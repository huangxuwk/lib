package com.mec.xml_parser.test;

public class AboutNativaBlock {
	public static int num = 10;
	
	{
		System.out.println("����һ�����ؿ�");
	}
	
	static {
		System.out.println("����һ����̬���ؿ�");
	}
	
	public AboutNativaBlock() {
	}
	
	public void fun() {
		System.out.println("ִ��fun()����");
	}
}
