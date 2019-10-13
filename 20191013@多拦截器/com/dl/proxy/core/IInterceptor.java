package com.dl.proxy.core;

public interface IInterceptor {
	boolean before(Object[] args) throws Throwable;
	Object after(Object object) throws Throwable;
	void dealThrowable(Throwable throwable) throws Throwable;
}
