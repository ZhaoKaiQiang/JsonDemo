/*
 * Copyright (c) 2014, 青岛司通科技有限公司 All rights reserved.
 * File Name：JacksonMapper.java
 * Version：V1.0
 * Author：zhaokaiqiang
 * Date：2014-11-27
 */

package com.example.jsondemo;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 * @ClassName: com.example.jsondemo.JacksonMapper
 * @Description:ObjectMapper的单例模式
 * @author zhaokaiqiang
 * @date 2014-11-27 下午4:06:52
 * 
 */
public class JacksonMapper {

	private static final ObjectMapper mapper = new ObjectMapper();

	private JacksonMapper() {
	}

	public static ObjectMapper getInstance() {
		return mapper;
	}

}