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
		System.out.println("成员如下：");
		Field[] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			// 获取变量类型和变量名
			System.out.println(field.getType().getSimpleName() + " " + field.getName());
		}
		
		System.out.println("方法如下：");
		Method[] methods = klass.getDeclaredMethods();
		for (Method method : methods) {
			// 获取返回值类型和方法名
			System.out.println(method.getReturnType().getSimpleName() + " " + method.getName() + "()");
		}
		
		System.out.println("构造方法：");
		Constructor<?>[] constructors = klass.getDeclaredConstructors();
		for (Constructor<?> constructor : constructors) {
			System.out.println(constructor);
		}
		
		// 创建获取的类类型的对象
		Object object = klass.newInstance();
		
		String fieldName = PropertiesParser.value("field");
		double v = Double.valueOf(PropertiesParser.value("value"));
		Field field = klass.getDeclaredField(fieldName);
		// 不管成员是什么权限修饰的，都可以设置为可见
		field.setAccessible(true);
		field.set(object, v);
		
		// 获取方法，并调用
		String methodName = PropertiesParser.value("method");
		Method method = klass.getDeclaredMethod(methodName);
		Object result = method.invoke(object);
		System.out.println(result);
	}
	
}
