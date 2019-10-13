package com.dl.proxy.core;

public abstract class BaseProxy {
	abstract Object innerProxy(Class<?> klass, Object object);
	abstract Object getObject();
	
	@SuppressWarnings("unchecked")
	public <T> T  getProxy() {
		Object object = getObject();
		return (T) innerProxy(object.getClass(), object);
	}
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<?> klass) {
		Object object = null;
		try {
			object = klass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return (T) innerProxy(klass, object);
	}
}
