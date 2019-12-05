package com.mec.uddi.communication;

public class NetMessage {
	private ENetCommand command;
	private String action;
	private String para;
	
	public NetMessage() {
	}

	public NetMessage(String message) {
		int dotIndex;
		
		dotIndex = message.indexOf('.');
		if (dotIndex < 0) {
			return;
		}
		String str = message.substring(0, dotIndex);
		this.command = ENetCommand.valueOf(str);
		
		message = message.substring(dotIndex + 1);
		dotIndex = message.indexOf('.');
		if (dotIndex < 0) {
			this.command = null;
			return;
		}
		str = message.substring(0, dotIndex); 
		this.action = str.equals(" ") ? null : str;
		
		message = message.substring(dotIndex + 1);
		this.para = message;
	}
	
	public ENetCommand getCommand() {
		return command;
	}

	public NetMessage setCommand(ENetCommand command) {
		this.command = command;
		return this;
	}

	public String getAction() {
		return action;
	}

	public NetMessage setAction(String action) {
		this.action = action;
		return this;
	}

	public String getPara() {
		return para;
	}

	public NetMessage setPara(String para) {
		this.para = para;
		return this;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer(command.name());
		result.append('.');
		result.append(action == null ? " " : action).append('.');
		result.append(para);
		
		return result.toString();
	}
	
}
