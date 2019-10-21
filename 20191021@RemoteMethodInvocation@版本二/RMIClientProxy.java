package com.dl.rmi.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RMIClientProxy {
	private RMIClient client;
	
	public RMIClientProxy() {
	}
	
	public RMIClientProxy(RMIClient client) {
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
						/*
						 * 短连接在此处需要做的是
						 * 连接服务器，向服务器发送方法名和参数
						 * 并接受服务器发送的执行方法后的结果
						 */
						client.connectToRMIServer();
						
						return client.invokeMethod(method, args);
					}
				});
	}
}
