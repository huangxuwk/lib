package com.mec.cs_framework.action;

import com.mec.util.String2Type;

public class ParameterModel {
	private Class<?> type;
	private Object value;
	
	public ParameterModel() {
	}

	public Class<?> getType() {
		return type;
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
	
}
