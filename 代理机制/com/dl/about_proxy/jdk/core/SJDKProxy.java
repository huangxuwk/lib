package com.dl.about_proxy.jdk.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 此方法适用于对接口中的方法进行拦截
 * 而对于普通方法则不行
 * 系统生成目标类的所有接口的子实现类
 * 通过反射机制调用目标类中的实现方法
 * 在调用过程中可进行拦截
 * @author dl
 *
 */
public class SJDKProxy {
	private Object object;
	
	public SJDKProxy() {
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy() {
		if (object == null) {
			return null;
		}
		
		Class<?> klass = object.getClass();
		
		return (T) Proxy.newProxyInstance(
				klass.getClassLoader(),
				klass.getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("前置拦截");
						
						Object result = method.invoke(object, args);
						
						System.out.println("后置拦截");
						return result;
					}
				});
	}
	
	public <T> T getProxy(Class<?> klass) {
		Object object = null;
		try {
			object = klass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		this.object = object;
		
		return getProxy();
	}
	
}
