package com.dl.about_proxy.eg;

import com.dl.about_proxy.cglib.core.SCglibProxy;
import com.dl.about_proxy.jdk.core.SJDKProxy;

public class Demo {
	
		public static void main(String[] args) {
			SJDKProxy sjdk = new SJDKProxy();
			ISomeClass some = sjdk.getProxy(SomeClass.class);
			System.out.println(some.doDealString("111111"));
			
			// ϵͳ���ж��̳е����Ƿ��Ѿ��̳й�������̳й�������õ���ģʽ
			SCglibProxy scglibProxy = new SCglibProxy();
			Complex complex1 = scglibProxy.getProxy(Complex.class);
			System.out.println(complex1.toString());
			System.out.println("-----------------------------------");
			SCglibProxy cglibProxy = new SCglibProxy();
			Complex complex2 = cglibProxy.getProxy(Complex.class);
			System.out.println(complex2.toString());
		}
}
