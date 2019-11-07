package com.mec.cs_framework.action;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ActionDefinition {
	private Class<?> klass;
	private Method method;
	private List<ParameterModel> parameters;
	private Object object;
	
	public ActionDefinition() {
		parameters = new ArrayList<>();
	}
	
	public Method getMethod() {
		return method;
	}

	public Object getObject() {
		return object;
	}
	
	public void addParameter(String propertyName, String propertyType) {
		parameters.add(new ParameterModel(propertyName, propertyType));
	}

	public void setKlass(String className) throws Exception {
		this.klass = Class.forName(className);
		this.object = klass.newInstance();
	}
	
	public void setObject(Object object) {
		this.object = object;
	}

	private Class<?>[] getParaTypes() {
		if (parameters.isEmpty()) {
			return new Class<?>[] {};
		}

		int parameterCount = parameters.size();
		Class<?>[] paraTypes = new Class<?>[parameterCount];
		for (int i = 0; i < parameterCount; i++) {
			paraTypes[i] = parameters.get(i).getType();
		}
		
		return paraTypes;
	}
	
	public void setMethod(String methodName) throws Exception {
		this.method = klass.getDeclaredMethod(methodName, getParaTypes());
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append('(').append(klass.getName())
			.append(" - ").append(method).append(')');
		
		for (ParameterModel para : parameters) {
			result.append("\n\t").append(para);
		}
		
		return result.toString();
	}
	
}
