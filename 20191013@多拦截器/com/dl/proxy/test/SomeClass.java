package com.dl.proxy.test;

public class SomeClass implements ISomeClass {
	
	public SomeClass() {
	}

	@Override
	public String doDealString(String mess) {
		return null;
	}

	@Override
	public int doDealInt(int num) {
		return num + 1;
	}
	
	public void doSomething() {
		System.out.println("执行doSomething()方法");
	}

}
