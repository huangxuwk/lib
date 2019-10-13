package com.dl.proxy.test;

import com.dl.proxy.core.DlProxy;
import com.dl.proxy.core.IInterceptor;
import com.dl.proxy.core.Interceptor;

public class Test {

	public static void main(String[] args) {
		Interceptor interceptor = new Interceptor(new IInterceptor() {
			@Override
			public void dealThrowable(Throwable throwable) throws Throwable {
				throw throwable;
			}
			@Override
			public boolean before(Object[] args) throws Throwable {
				System.out.println("第一次前置拦截");
				return true;
			}
			@Override
			public Object after(Object object) throws Throwable {
				System.out.println("第一次后置拦截");
				return object;
			}
		});
		interceptor.addInterceptor(new IInterceptor() {
			@Override
			public void dealThrowable(Throwable throwable) throws Throwable {
				throw throwable;
			}
			@Override
			public boolean before(Object[] args) throws Throwable {
				System.out.println("第二次前置拦截");
				return true;
			}
			@Override
			public Object after(Object object) throws Throwable {
				System.out.println("第二次后置拦截");
				return object;
			}
		});
		
		DlProxy dlProxy = new DlProxy(interceptor);
//		Complex complex = dlProxy.getProxy(Complex.class);
//		System.out.println(complex.getReal());
		ISomeClass jdkProxy = dlProxy.getProxy(SomeClass.class);
		System.out.println(jdkProxy.doDealString("测试接口"));
	}

}
