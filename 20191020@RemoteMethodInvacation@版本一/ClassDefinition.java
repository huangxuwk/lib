package com.dl.rmi.core;

import java.lang.reflect.Method;

public class ClassDefinition {
	private Class<?> klass;
	private Object object;
	
	public ClassDefinition() {
	}

	public ClassDefinition(Class<?> klass, Object object) {
		this.klass = klass;
		this.object = object;
	}

	void setObject(Object object) {
		this.object = object;
	}

	void setKlass(Class<?> klass) {
		this.klass = klass;
	}
	
	Object getMethodReturn(String methodName, Object[] args, Class<?>[] parametersType) {
		if (args == null) {
			args = new Object[]{};
		}
		Object result = null;
		try {
			Method method = klass.getDeclaredMethod(methodName, parametersType);
			result = method.invoke(object, args);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	/*
	Object getMethodReturn(String methodName, Object[] args) {
		Method[] methods = klass.getMethods();
		Object result = null;
		for (int index = 0; index < methods.length; index++) {
			Method method = methods[index];
			if (methodName.equals(method.getName())) {
				if (judgeArgs(args, method.getParameterTypes())) {
					try {
						result = method.invoke(object, args);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}
	
	private boolean judgeArgs(Object[] args1, Class<?>[] args2) {
		int length = args1.length;
		if (length != args2.length) {
			return false;
		}
		
		for (int index = 0; index < length; index++) {
			if (args1[index].getClass() != args2[index]) {
				return false;
			}
		}
		return true;
	}
	 */
}
