package com.dl.test;

import com.dl.server.core.ProxyAnntotation;

@ProxyAnntotation(interfaces = {TestInter.class})
public class Test implements TestInter {

	@Override
	public void printOneToTen() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}
	}

	@Override
	public String show(String str) {
		return null;
	}

	@Override
	public int add(int one, int two) {
		return one + two;
	}

}
