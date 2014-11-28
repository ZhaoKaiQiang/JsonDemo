/*
 * Copyright (c) 2014, 青岛司通科技有限公司 All rights reserved.
 * File Name：GsonTest.java
 * Version：V1.0
 * Author：zhaokaiqiang
 * Date：2014-11-26
 */

package com.example.jsondemo;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.test.AndroidTestCase;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class GsonTest extends AndroidTestCase {

	public static final String TAG = "GsonTest";

	/**
	 * 单一对象的json生成与解析
	 */
	public void objectToJson() {

		Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));
		Gson gson = new Gson();

		String jsonString = gson.toJson(p);

		Log.d(TAG, "---------单一对象生成--------");
		Log.d(TAG, jsonString);

		Person person = gson.fromJson(jsonString, Person.class);

		Log.d(TAG, "---------单一对象解析--------");
		Log.d(TAG, person.toString());

	}

	/**
	 * 集合对象的json生成与解析
	 */
	public void objectsToJson() {

		Gson gson = new Gson();
		Person person = new Person("zhaokaiqiang", 22,
				new Birthday(1992, 1, 19));
		ArrayList<Person> arrayList = new ArrayList<Person>();
		arrayList.add(person);
		arrayList.add(person);
		arrayList.add(person);

		String jsonString = gson.toJson(arrayList);

		Log.d(TAG, "---------集合对象生成--------");
		Log.d(TAG, jsonString);

		Type type = new TypeToken<ArrayList<Person>>() {
		}.getType();

		ArrayList<Person> persons = gson.fromJson(jsonString, type);

		Log.d(TAG, "---------集合对象解析--------");
		Log.d(TAG, persons.toString());
	}

	public void fromJsonString() {

		Gson gson = new Gson();
		JsonElement jsonElement = new JsonParser().parse(getJsonString());
		
		JsonObject jsonObject = jsonElement.getAsJsonObject();

		JsonElement personElement = jsonObject.get("person");
		Person person = gson.fromJson(personElement, Person.class);

		JsonElement nameElement = jsonObject.get("name");
		String name = nameElement.getAsString();

		JsonElement ageElement = jsonObject.get("age");
		int age = ageElement.getAsInt();

		Log.d(TAG, "person-----" + person);
		Log.d(TAG, "name-----" + name);
		Log.d(TAG, "age-----" + age);

	}

	public String getJsonString() {
		Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));
		JsonElement jsonElement = new JsonParser().parse(new Gson().toJson(p));

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "zhao");
		jsonObject.addProperty("age", 22);
		jsonObject.add("person", jsonElement);
		Log.d(TAG, "getJsonString------" + jsonObject.toString());
		return jsonObject.toString();
	}

}
