package com.hx.cs_framework.core;

/*
 * 网络信息
 * 是处理接收到的信息
 * 和要发送的信息
 * 分离格式，内容
 */
public class NetMessage {
	private EMessageType type;
	private String action;
	private String parameter;
	
	NetMessage() {
	}
	
	// 将接收到的信息转化为分离的成分，以便于判断命令
	NetMessage(String message) {
		 //  TO_ONE:getStudentInfo:你好		
		int index = message.indexOf(':');
		String typeStr = message.substring(0, index);
		type = EMessageType.valueOf(typeStr);
		message = message.substring(index+1);
		 //  getStudentInfo:你好
		index = message.indexOf(':');
		action = message.substring(0, index);
		action = action.length() <= 0 ? null : action;
		// 你好
		this.parameter = message.substring(index+1);
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
		/*
		 * 假设：
		 * type 为 TO_ONE
		 * action 为 getStudentInfo
		 * parameter 为 你好
		 * 转换后为
		 * TO_ONE:getStudentInfo:你好
		 */
		StringBuffer res = new StringBuffer();
		res.append(type.name()).append(':')
				.append(action == null ? "" : action).append(':')
				.append(parameter);
		
		return res.toString();
	}
}
