package com.dl.spring_imitate.core;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class BeanMethodDefinition {
	private Class<?> klass;
	private Object object;
	private Method method;
	int parameterCount;

	BeanMethodDefinition() {
	}

	Class<?> getKlass() {
		return klass;
	}

	void setKlass(Class<?> klass) {
		this.klass = klass;
	}

	Object getObject() {
		return object;
	}

	void setObject(Object object) {
		this.object = object;
	}

	Method getMethod() {
		return method;
	}

	void setMethod(Method method) {
		this.method = method;
		
		Parameter[] parameters = method.getParameters();
		parameterCount = parameters.length;
	}
	
	int decrease() {
		return --parameterCount;
	}

}
