package com.mec.cs_framework.action;

import com.mec.util.String2Type;

public class ParameterModel {
	private String name;
	private Class<?> type;
	private Object value;
	
	public ParameterModel() {
	}

	public ParameterModel(String name, String type) {
		setName(name);
		setType(type);
	}
	
	public Class<?> getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		try {
			this.type = String2Type.toType(type);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return name + " " + type;
	}
	
}
