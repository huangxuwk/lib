package com.dl.rmi.core;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import com.dl.util.ArgumentMaker;

public class ServerDealMethod {
	private Class<?>[] parametersType;
	
	public ServerDealMethod() {
	}
	
	public Object invokeMethod(String interfacesName, String methodName, String argsString) {
		ClassFactory classFactory = new ClassFactory();
		
		Class<?> klass = null;
		try {
			klass = Class.forName(interfacesName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ClassDefinition classDefinition = classFactory.getClassDefinition(klass);
		if (classDefinition == null) {
			System.out.println("classDefinition为空");
			return null;
		}
		Method[] methods = getMethodsByName(klass, methodName);
		Object[] args = null;
		if (!argsString.equals("")) {
			ArgumentMaker maker = new ArgumentMaker(argsString);
			args = getObjects(methods, maker);
		}
		
		return classDefinition.getMethodReturn(methodName, args, parametersType);
	}
	
	private Method[] getMethodsByName(Class<?> klass, String methodName) {
		Method[] methods = klass.getDeclaredMethods();
		List<Method> methodList = new ArrayList<>();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				methodList.add(method);
			}
		}
		for (int index = 0; index < methodList.size(); index++) {
			methods[index] = methodList.get(index);
		}
		return methods;
	}
	
	private Object[] getObjects(Method[] methods, ArgumentMaker maker) {
		Object[] objects = null;
		for (Method method : methods) {
			Parameter[] parameters = method.getParameters();
			int length = parameters.length;
			if (maker.getValuesCount() != length) {
				continue;
			}
			objects = new Object[length];
			Class<?>[] parametersType = new Class<?>[length];
			int index = 0;
			for (; index < length; index++) {
				// getParameterizedType() 可以处理泛型参数
				parametersType[index] = (Class<?>) parameters[index].getParameterizedType();
				objects[index] = maker.getValue("arg" + index, parametersType[index]);
				if (objects[index] == null) {
					objects = null;
					break;
				}
			}
			if (index == length) {
				this.parametersType = parametersType;
				break;
			}
		}
		
		return objects;
	}
	
}
