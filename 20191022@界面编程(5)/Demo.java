package com.mec.about_listener.core;

public class Demo {
	public static void main(String[] args) {
		MecMainClass one = new MecMainClass("一些信号");
		
		one.addListener(new IMecListener() {
			@Override
			public void somethingHappenning(String message) {
				System.out.println("发生了一些事情");
			}
			
			@Override
			public boolean isOk() {
				return false;
			}
		});
		
		/*
		 * 这一步由 jvm 做出
		 */
		one.fun();
	}
}
