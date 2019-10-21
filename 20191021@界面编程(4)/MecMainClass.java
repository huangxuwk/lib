package com.mec.about_listener.core;

public class MecMainClass {
	private String someMessage;
	private IMecListener listener;
	
	public MecMainClass(String someMessage) {
		this.someMessage = someMessage;
	}
	
	private void dosomething() {
		if (listener == null) {
			return;
		}
		listener.somethingHappenning(someMessage);
	}
	
	public void fun() {
		dosomething();
	}
	
	public void addListener(IMecListener listener) {
		this.listener = listener;
	}
	
}
