/*
 * Copyright (c) 2014, 青岛司通科技有限公司 All rights reserved.
 * File Name：JacksonTest.java
 * Version：V1.0
 * Author：zhaokaiqiang
 * Date：2014-11-27
 */

package com.example.jsondemo;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import android.test.AndroidTestCase;
import android.util.Log;

public class JacksonTest extends AndroidTestCase {

	private static final String TAG = "JacksonTest";

	/**
	 * 创建Array形式的json
	 * 
	 * @throws Exception
	 */
	public void createArrayJson() throws Exception {

		Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));

		StringWriter stringWriter = new StringWriter();
		// 只能通过这种方式获取
		JsonGenerator jsonGenerator = JacksonMapper.getInstance()
				.getJsonFactory().createJsonGenerator(stringWriter);

		jsonGenerator.writeStartArray();
		jsonGenerator.writeString("zhaokaiqiang");
		jsonGenerator.writeNumber(22);
		jsonGenerator.writeObject(p);
		jsonGenerator.writeEndArray();

		jsonGenerator.flush();
		jsonGenerator.close();

		Log.d(TAG, stringWriter.toString());
	}

	/**
	 * 生成Object形式的json
	 * 
	 * @throws Exception
	 */
	public String createObjectJson() throws Exception {

		Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));

		StringWriter stringWriter = new StringWriter();

		// 必须通过这种方式获取
		JsonGenerator jsonGenerator = JacksonMapper.getInstance()
				.getJsonFactory().createJsonGenerator(stringWriter);

		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("name", "zhaokaiqiang");
		jsonGenerator.writeNumberField("age", 22);
		jsonGenerator.writeObjectField("person", p);
		jsonGenerator.writeEndObject();

		jsonGenerator.flush();
		jsonGenerator.close();

		return stringWriter.toString();

	}

	/**
	 * json转换为集合对象
	 * 
	 * @throws Exception
	 */
	public void jsonToObjects() throws Exception {

		Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));

		ArrayList<Person> persons = new ArrayList<Person>();
		persons.add(p);
		persons.add(p);
		persons.add(p);

		@SuppressWarnings("unchecked")
		ArrayList<Person> personArrayList = JacksonMapper.getInstance()
				.readValue(getJsonString(persons),
						new ArrayList<Person>().getClass());
		Log.d(TAG, "persons------" + personArrayList);
	}

	/**
	 * json转换为单一对象
	 * 
	 * @throws Exception
	 */
	public void jsonToObject() throws Exception {
		Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));
		Person person = JacksonMapper.getInstance().readValue(getJsonString(p),
				new Person().getClass());
		Log.d(TAG, "person------" + person);
	}

	/**
	 * 集合对象
	 * 
	 * @throws Exception
	 */
	public void objectsToJson() throws Exception {
		Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));

		ArrayList<Person> persons = new ArrayList<Person>();
		persons.add(p);
		persons.add(p);
		persons.add(p);

		Log.d(TAG, getJsonString(persons));
	}

	/**
	 * 单一对象
	 * 
	 * @throws Exception
	 */
	public void objectToJson() throws Exception {
		Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));
		Log.d(TAG, getJsonString(p));
	}

	/**
	 * 获取Json数据方法一
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public String getJsonString1(Object object) throws Exception {
		ObjectMapper mapper = JacksonMapper.getInstance();
		StringWriter sw = new StringWriter();
		JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
		mapper.writeValue(gen, object);
		gen.flush();
		gen.close();
		return sw.toString();
	}

	/**
	 * 获取Json数据方法二
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public String getJsonString(Object object) throws Exception {
		return JacksonMapper.getInstance().writeValueAsString(object);
	}

	public void toObject() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));
		String jsonString = getJsonString(p);
		Person person = objectMapper.readValue(jsonString, Person.class);
		Log.d(TAG, person.toString());

	}

	public String toObjects() throws Exception {

		Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));

		ArrayList<Person> persons = new ArrayList<Person>();
		persons.add(p);
		persons.add(p);
		persons.add(p);

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = getJsonString(persons);
		@SuppressWarnings("unchecked")
		ArrayList<Person> arrayList = objectMapper.readValue(jsonString,
				new ArrayList<Person>().getClass());
		return arrayList.toString();

	}

	public void fromJson() throws Exception {

		String jsonString = createObjectJson();
		ObjectMapper objectMapper = JacksonMapper.getInstance();
		Map<String, Map<String, Object>> objects = objectMapper.readValue(
				jsonString,
				new TypeReference<Map<String, Map<String, Object>>>() {
				});
		Set<String> keys = objects.keySet();

		Log.d(TAG, keys.toString());

		String name = "";
		String age = "";
		String personString = "";
		for (Iterator<String> key = keys.iterator(); key.hasNext();) {

			if (key.next().equals("name")) {
				name = objects.get(key).toString();
			} else if (key.next().equals("age")) {
				age = objects.get(key).toString();
			} else if (key.next().equals("person")) {
				personString = objects.get(key).toString();
			}

			Log.d(TAG, "key=" + key);

		}

		Log.d(TAG, "name----" + name);
		Log.d(TAG, "age----" + age);
		Log.d(TAG, "person----" + personString);

	}

	public void fromJsons() throws Exception {

		String jsonString = createObjectJson();
		ObjectMapper objectMapper = JacksonMapper.getInstance();
		
		JsonNode jsonNode = objectMapper.readTree(jsonString);
		JsonNode nameNode = jsonNode.get("name");
		JsonNode ageNode = jsonNode.get("age");
		JsonNode persoNode = jsonNode.get("person");
		
		String personString = persoNode.toString();
		Person person = objectMapper.readValue(personString, Person.class);

		Log.d(TAG, "person=" + person.toString());
		Log.d(TAG, "age=" + ageNode.asInt());
		Log.d(TAG, "name=" + nameNode.asText());

	}

}
