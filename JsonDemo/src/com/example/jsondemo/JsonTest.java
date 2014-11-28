/*
 * Copyright (c) 2014, 青岛司通科技有限公司 All rights reserved.
 * File Name：JsonTest.java
 * Version：V1.0
 * Author：zhaokaiqiang
 * Date：2014-11-26
 */

package com.example.jsondemo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import android.test.AndroidTestCase;
import android.util.Log;

public class JsonTest extends AndroidTestCase {

	private static final String TAG = "JsonTest";

	/**
	 * 对象转成json格式
	 * 
	 * @throws Exception
	 */
	public void objectsToJson() throws Exception {

		ArrayList<Person> persons = new ArrayList<Person>();

		Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));

		persons.add(p);
		persons.add(p);
		persons.add(p);

		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		int length = persons.size();
		for (int i = 0; i < length; i++) {
			Person person = persons.get(i);
			JSONObject personObject = new JSONObject();
			personObject.put("name", person.getName());
			personObject.put("age", person.getAge());
			personObject.put("birthday", person.getBirthday());
			jsonArray.put(personObject);
		}

		jsonObject.put("persons", jsonArray);
		Log.d(TAG, jsonObject.toString());
	}

	public void objectToJson() throws Exception {

		JSONArray jsonArray = new JSONArray();
		Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));
		jsonArray.put("toJson");
		jsonArray.put(100);
		jsonArray.put(true);
		jsonArray.put(p);

		Log.d(TAG, jsonArray.toString());

	}

	public void fromJson() throws Exception {

		JSONStringer jsonStringer = new JSONStringer();

		jsonStringer.object();
		jsonStringer.key("name");
		jsonStringer.value("zhaokaiqiang");
		jsonStringer.key("age");
		jsonStringer.value(22);
		jsonStringer.endObject();

		String jsonString = jsonStringer.toString();
		Log.d(TAG, "生成的json----------" + jsonString);

		JSONTokener jsonTokener = new JSONTokener(jsonString);
		JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
		String name = jsonObject.getString("name");
		int age = jsonObject.getInt("age");
		Log.d(TAG, "name=" + name);
		Log.d(TAG, "age=" + age);
		
	}

	public void toJson() {
		// json数组
		// JSONArray
		// json对象
		// JSONObject
		// json解析异常
		// JSONException
		// 用来构造一个json格式的字符串
		// JSONStringer
		// JSONTokener json = new JSONTokener("");

	}

}
