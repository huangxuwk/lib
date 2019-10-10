package com.dl.spring_imitate.core;

public class BeanDefinition {
	private Class<?> klass;
	private Object object;
	private boolean inject;
	
	BeanDefinition() {
		this.inject = false;
	}

	boolean isInject() {
		return inject;
	}

	void setInject(boolean inject) {
		this.inject = inject;
	}

	Class<?> getKlass() {
		return klass;
	}

	void setKlass(Class<?> klass) {
		this.klass = klass;
	}

	Object getObject() {
		return object;
	}

	void setObject(Object object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return klass.getSimpleName() + " : " + object;
	}
	
}
