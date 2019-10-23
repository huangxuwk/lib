package com.mec.xml_parser.test;

public class AboutNativaBlock {
	public static int num = 10;
	
	{
		System.out.println("这是一个本地块");
	}
	
	static {
		System.out.println("这是一个静态本地块");
	}
	
	public AboutNativaBlock() {
	}
	
	public void fun() {
		System.out.println("执行fun()方法");
	}
}
