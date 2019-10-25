package com.hx.about_properties;

import com.util.PropertiesParser;

public class Test {
	
	public static void main(String[] args) {
		// 通过这个工具，只用加载一次文件，以后就可以直接通过工具读取文件里的值
		PropertiesParser.loadProperties("/myFirst.properties");
		boolean sex = Boolean.valueOf(PropertiesParser.value("sex"));
		System.out.println(sex);
		
		int age = Integer.valueOf(PropertiesParser.value("age"));
		System.out.println(age);
	}
}
