package com.util.demo;

import com.util.PropertiesParser;

public class Test {
	
		public static void main(String[] args) {
			PropertiesParser.loadProperties("/demo.properties");
			
			System.out.println("name: " + PropertiesParser.value("name"));
			System.out.println("age: " + PropertiesParser.value("age"));
			System.out.println("sex: " + PropertiesParser.value("sex"));
		}
}
