package com.dl.about_proxy.cglib.core;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * �˷�����������ͨ�࣬���ԶԷǽӿ��෽����������
 * @author dl
 *
 */
public class SCglibProxy {
	private Object object;
	
	public SCglibProxy() {
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
		
		Class<?> parentClass = object.getClass();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(parentClass);
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				// proxy �� object �����࣬����ϵͳ�̳���object ��
				System.out.println("ǰ������");
				Object result = method.invoke(object, args);
				System.out.println("��������");
				return result;
			}
		});
		
		return (T) enhancer.create();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<?> klass) {
		try {
			Object object = klass.newInstance();
			this.object = object;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return (T) getProxy();
	}
	
}
