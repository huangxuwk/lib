package com.hx.about_reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.util.PropertiesParser;

public class TestForReflect {

	public TestForReflect() {
	}
	
	public static void main(String[] args) throws ClassNotFoundException, Exception {
		PropertiesParser.loadProperties("/MySecondFile.properties");
		String className = PropertiesParser.value("name");
		Class<?> klass = Class.forName(className);
		
//		System.out.println(klass.getName());
		System.out.println("��Ա���£�");
		Field[] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			// ��ȡ�������ͺͱ�����
			System.out.println(field.getType().getSimpleName() + " " + field.getName());
		}
		
		System.out.println("�������£�");
		Method[] methods = klass.getDeclaredMethods();
		for (Method method : methods) {
			// ��ȡ����ֵ���ͺͷ�����
			System.out.println(method.getReturnType().getSimpleName() + " " + method.getName() + "()");
		}
		
		System.out.println("���췽����");
		Constructor<?>[] constructors = klass.getDeclaredConstructors();
		for (Constructor<?> constructor : constructors) {
			System.out.println(constructor);
		}
		
		// ������ȡ�������͵Ķ���
		Object object = klass.newInstance();
		
		String fieldName = PropertiesParser.value("field");
		double v = Double.valueOf(PropertiesParser.value("value"));
		Field field = klass.getDeclaredField(fieldName);
		// ���ܳ�Ա��ʲôȨ�����εģ�����������Ϊ�ɼ�
		field.setAccessible(true);
		field.set(object, v);
		
		// ��ȡ������������
		String methodName = PropertiesParser.value("method");
		Method method = klass.getDeclaredMethod(methodName);
		Object result = method.invoke(object);
		System.out.println(result);
	}
	
}
