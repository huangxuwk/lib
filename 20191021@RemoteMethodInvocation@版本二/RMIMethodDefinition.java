package com.dl.rmi.core;

import java.lang.reflect.Method;

public class RMIMethodDefinition {
	private Object object;
	private Method method;
	
	RMIMethodDefinition() {
	}

	RMIMethodDefinition(Object object, Method method) {
		this.object = object;
		this.method = method;
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
	}
	
}
