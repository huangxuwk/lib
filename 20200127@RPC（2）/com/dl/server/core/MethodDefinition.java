package com.dl.server.core;

import java.lang.reflect.Method;

/**
 * ���淽����Ϣ����<br>
 * ��ɨ�赽�ķ����Ͷ�Ӧ����Ͷ��󱣴棻
 * 
 * @author dl
 *
 */
public class MethodDefinition {
	private Class<?> klass;
	private Object object;
	private Method method;

	public MethodDefinition() {
	}
	
	public MethodDefinition(Class<?> klass, Object object, Method method) {
		this.klass = klass;
		this.object = object;
		this.method = method;
	}
	
	public Class<?> getKlass() {
		return klass;
	}

	public void setKlass(Class<?> klass) {
		this.klass = klass;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

}
