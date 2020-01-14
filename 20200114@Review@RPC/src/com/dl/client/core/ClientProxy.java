package com.dl.client.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientProxy {
	private RPCClient client;
	
	public ClientProxy() {
	}
	
	public void setClient(RPCClient client) {
		this.client = client;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T jdkProxy(Class<?> klass) {
		ClassLoader classLoader = klass.getClassLoader();
		Class<?>[] interfaces = klass.getInterfaces();
		return (T) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//				method.invoke(object, args);
				return client.remoteProcedureCall(method, args);
			}
		});
	}
}
