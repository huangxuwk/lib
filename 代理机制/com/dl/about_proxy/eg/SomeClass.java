package com.dl.about_proxy.eg;

public class SomeClass implements ISomeClass {
	
	public SomeClass() {
	}

	@Override
	public String doDealString(String mess) {
		System.out.println("ִ�з����С���");
		return "[" + mess + "]";
	}

	@Override
	public int doDealInt(int num) {
		return num + 1;
	}
	
	public void doSomething() {
		System.out.println("���������JDK��������в��ᱻ���أ�");
	}

}
