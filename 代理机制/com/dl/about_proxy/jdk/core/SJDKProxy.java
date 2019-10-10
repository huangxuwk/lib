package com.dl.about_proxy.jdk.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * �˷��������ڶԽӿ��еķ�����������
 * ��������ͨ��������
 * ϵͳ����Ŀ��������нӿڵ���ʵ����
 * ͨ��������Ƶ���Ŀ�����е�ʵ�ַ���
 * �ڵ��ù����пɽ�������
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
						System.out.println("ǰ������");
						
						Object result = method.invoke(object, args);
						
						System.out.println("��������");
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
