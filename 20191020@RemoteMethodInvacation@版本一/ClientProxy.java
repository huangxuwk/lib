package com.dl.rmi.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientProxy {
	private RMIClient client;
	
	public ClientProxy() {
	}
	
	public ClientProxy(RMIClient client) {
		this.client = client;
	}
	
	public void setClient(RMIClient client) {
		this.client = client;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<?> interfaces) {
		return (T) Proxy.newProxyInstance(
				interfaces.getClassLoader(),
				new Class<?>[] {interfaces},
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						client.connectToRMIServer();
						
						return client.invokeMethod(interfaces, method, args);
					}
				});
	}
}
