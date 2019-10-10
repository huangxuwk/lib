package com.dl.about_proxy.eg;

public class SomeClass implements ISomeClass {
	
	public SomeClass() {
	}

	@Override
	public String doDealString(String mess) {
		System.out.println("执行方法中……");
		return "[" + mess + "]";
	}

	@Override
	public int doDealInt(int num) {
		return num + 1;
	}
	
	public void doSomething() {
		System.out.println("这个方法在JDK代理机制中不会被拦截！");
	}

}
