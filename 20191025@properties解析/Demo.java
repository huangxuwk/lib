package com.hx.about_properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Demo {
	
	public Demo() {
	}
	
	public void fun() {
			try {
				Properties properties = new Properties();
				InputStream is = Test.class.getResourceAsStream("/myFirst.properties");
				properties.load(is);
				
				String sex = properties.getProperty("sex");
				System.out.println(sex);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void function() {
		try {
			Properties properties = new Properties();
			InputStream is = Test.class.getResourceAsStream("/myFirst.properties");
			properties.load(is);
			
			String name = properties.getProperty("name");
			System.out.println(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
}

