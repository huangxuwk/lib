package com.hx.cs_framework.core;

/*
 * ������Ϣ
 * �Ǵ�����յ�����Ϣ
 * ��Ҫ���͵���Ϣ
 * �����ʽ������
 */
public class NetMessage {
	private EMessageType type;
	private String action;
	private String parameter;
	
	NetMessage() {
	}
	
	// �����յ�����Ϣת��Ϊ����ĳɷ֣��Ա����ж�����
	NetMessage(String message) {
		 //  TO_ONE:getStudentInfo:���		
		int index = message.indexOf(':');
		String typeStr = message.substring(0, index);
		type = EMessageType.valueOf(typeStr);
		message = message.substring(index+1);
		 //  getStudentInfo:���
		index = message.indexOf(':');
		action = message.substring(0, index);
		action = action.length() <= 0 ? null : action;
		// ���
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
		 * ���裺
		 * type Ϊ TO_ONE
		 * action Ϊ getStudentInfo
		 * parameter Ϊ ���
		 * ת����Ϊ
		 * TO_ONE:getStudentInfo:���
		 */
		StringBuffer res = new StringBuffer();
		res.append(type.name()).append(':')
				.append(action == null ? "" : action).append(':')
				.append(parameter);
		
		return res.toString();
	}
}
