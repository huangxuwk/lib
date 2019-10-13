package com.dl.proxy.core;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLIBProxy extends BaseProxy {
	private DlProxy dlProxy;

	CGLIBProxy() {
	}
	
	CGLIBProxy(DlProxy dlProxy) {
		this.dlProxy = dlProxy;
	}

	void setDlProxy(DlProxy dlProxy) {
		this.dlProxy = dlProxy;
	}
	
	@Override
	Object innerProxy(Class<?> klass, Object object) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(klass);
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				Interceptor interceptor = dlProxy.getInterceptor();
				
				Object result = null;
				result = interceptor.dealBefore(method, args, object);
				
				return result;
			}
		});
		
		return enhancer.create();
	}

	@Override
	Object getObject() {
		return dlProxy.getObject();
	}

}
