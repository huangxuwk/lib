package com.mec.about_listener.core;

public class Demo {
	public static void main(String[] args) {
		MecMainClass one = new MecMainClass("һЩ�ź�");
		
		one.addListener(new IMecListener() {
			@Override
			public void somethingHappenning(String message) {
				System.out.println("������һЩ����");
			}
			
			@Override
			public boolean isOk() {
				return false;
			}
		});
		
		/*
		 * ��һ���� jvm ����
		 */
		one.fun();
	}
}
