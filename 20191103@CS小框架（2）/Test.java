package com.hx.cs_framework.core;

public class Test {

	public static void main(String[] args) {
		NetMessage message = new NetMessage()
				.setType(EMessageType.YOUR_ID)
				.setParameter("nihao");
		System.out.println(message.toString());
		
		NetMessage mess = new NetMessage(message.toString());
		System.out.println(mess);
	}
}
