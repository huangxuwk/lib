package com.dl.proxy.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy extends BaseProxy {
	private DlProxy dlProxy;

	JDKProxy() {
	}
	
	JDKProxy(DlProxy dlProxy) {
		this.dlProxy = dlProxy;
	}

	void setDlProxy(DlProxy dlProxy) {
		this.dlProxy = dlProxy;
	}

	@Override
	Object innerProxy(Class<?> klass, Object object) {
		Class<?>[] interfaces = klass.getInterfaces();
		ClassLoader classLoader = klass.getClassLoader();
		
		return Proxy.newProxyInstance(classLoader, interfaces,
				new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Interceptor interceptor = dlProxy.getInterceptor();
				
				Object result = null;
				result = interceptor.dealBefore(method, args, object);
				
				return result;
			}
		});
	}

	@Override
	Object getObject() {
		return dlProxy.getObject();
	}

}
