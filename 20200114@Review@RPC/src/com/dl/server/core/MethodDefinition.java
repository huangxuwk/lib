package com.dl.server.core;

import java.lang.reflect.Method;

public class MethodDefinition {
	private Object object;
	private Method method;

	public MethodDefinition() {
	}
	
	public MethodDefinition(Object object, Method method) {
		this.object = object;
		this.method = method;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
	
}
