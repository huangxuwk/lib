package com.util;

import java.io.IOException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesParser {
	/*
	 * 保证 Map 只产生一个，避免反复从外存中读取文件的内容
	 * final 只是不改变首地址，并不是不能改变所指向空间的值
	 */
	private static final Map<String, String> propertyMap;
	
	static {
		propertyMap = new HashMap<>();
	}
	
	public PropertiesParser() {
	}

	public static void loadProperties(String path) {
		InputStream is = PropertiesParser.class.getResourceAsStream(path);
		loadProperties(is);
	}
	
	public static void loadProperties(InputStream is) {
		Properties properties = new Properties();
		
		// 这里的异常可以接收也可以抛出
		try {
			properties.load(is);
			Set<Object> keySet = properties.keySet();
			Iterator<Object> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				String value = properties.getProperty(key);
				propertyMap.put(key, value);
			}
 		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Set<String> keySet() {
		return propertyMap.keySet();
	}
	
	public static String value(String key) {
		return propertyMap.get(key);
	}
	
}

