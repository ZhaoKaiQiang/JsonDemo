/*
 * Copyright (c) 2014, 青岛司通科技有限公司 All rights reserved.
 * File Name：Birthday.java
 * Version：V1.0
 * Author：zhaokaiqiang
 * Date：2014-11-26
 */

package com.example.jsondemo;

/**
 * 
 * @ClassName: com.example.jsondemo.Birthday
 * @Description: 生日类
 * @author zhaokaiqiang
 * @date 2014-11-26 上午10:29:47
 * 
 */
public class Birthday {

	private int year;
	private int month;
	private int day;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Birthday() {
		super();
	}

	public Birthday(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}

	@Override
	public String toString() {
		return "Birthday [year=" + year + ", month=" + month + ", day=" + day
				+ "]";
	}

}
