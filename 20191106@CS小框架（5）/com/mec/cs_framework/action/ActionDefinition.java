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
	
	public void setKlass(String className) throws Exception {
		this.klass = Class.forName(className);
	}
	
	public void setMethod() {
		
	}
	
}
