package com.hx.about_properties;

import com.util.PropertiesParser;

public class Test {
	
	public static void main(String[] args) {
		// ͨ��������ߣ�ֻ�ü���һ���ļ����Ժ�Ϳ���ֱ��ͨ�����߶�ȡ�ļ����ֵ
		PropertiesParser.loadProperties("/myFirst.properties");
		boolean sex = Boolean.valueOf(PropertiesParser.value("sex"));
		System.out.println(sex);
		
		int age = Integer.valueOf(PropertiesParser.value("age"));
		System.out.println(age);
	}
}
