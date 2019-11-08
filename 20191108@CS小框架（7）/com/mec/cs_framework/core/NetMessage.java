package com.mec.cs_framework.core;

public class NetMessage {
	private EMessageType type;
	private String action;
	private String parameter;
	
	NetMessage() {
	}

	NetMessage(String mess) {
		// TO_ONE:getStudentInfo:你好
		int index = mess.indexOf(':');
		String trpeStr = mess.substring(0, index);
		this.type = EMessageType.valueOf(trpeStr);
		// getStudentInfo:你好
		mess = mess.substring(index + 1);
		index = mess.indexOf(':');
		action = mess.substring(0, index);
		this.action = action.length() <= 0 ? null : action;
		// 你好
		this.parameter = mess.substring(index+1);
	}
	
	EMessageType getType() {
		return type;
	}

	String getAction() {
		return action;
	}

	String getParameter() {
		return parameter;
	}

	NetMessage setType(EMessageType type) {
		this.type = type;
		return this;
	}

	NetMessage setAction(String action) {
		this.action = action;
		return this;
	}

	NetMessage setParameter(String parameter) {
		this.parameter = parameter;
		return this;
	}

	@Override
	public String toString() {
		// 假设:
		// type 为 TO_ONE
		// action 为 getStudentInfo
		// parameter 为 你好
		// 我希望转换成字符串：
		// TO_ONE:getStudentInfo:你好
		StringBuffer res = new StringBuffer();
		res.append(type.name()).append(':')
				.append(action == null ? "" : action).append(':')
				.append(parameter);
		
		return res.toString();
	}
	
}
