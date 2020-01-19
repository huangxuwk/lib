package com.dl.test;

import com.dl.registry.core.DealMessage;
import com.dl.registry.core.RegistrationCenter;
import com.dl.server.core.MethodFactory;

public class TestForCenter {

	public static void main(String[] args) {
		MethodFactory.scanPackage("com.dl.test");
		
		RegistrationCenter center = new RegistrationCenter();
		center.setDealMessage(new DealMessage());
		center.startup();
		
//		DealMessage m = new DealMessage();
//		
//		NetMessage me = new NetMessage();
//		me.setType(EMessageType.REGISTRY).setParamater("111");
//		m.dealMessage(new Communication() {
//			
//			@Override
//			public void dealMessage(Communication communication, String message) {
//				// TODO Auto-generated method stub
//				
//			}
//		}, me.toString());
	}

}
