package com.dl.proxy.core;

import java.lang.reflect.Method;

/*
 * 本类用来处理多拦截器问题，
 */
public class Interceptor {
	private Interceptor next;
	private IInterceptor interceptor;
	private boolean success;
	
	public Interceptor() {
		success = true;
	}
	
	public Interceptor(IInterceptor interceptor) {
		this();
		this.interceptor = interceptor;
	}
	
	boolean isSuccess() {
		return success;
	}

	// 模拟实现链表
	public void addInterceptor(IInterceptor interceptor) {
		if (next == null) {
			next = new Interceptor(interceptor);
		} else {
			next.addInterceptor(interceptor);
		}
	}
	
	Object invokeMethod(Method method, Object[] args, Object object) throws Throwable {
		Object result = null;
		try {
			result = method.invoke(object, args);
		} catch (Exception e) {
			interceptor.dealThrowable(e);
		}
		return result;
	}
	
	Object dealBefore(Method method, Object[] args, Object object) throws Throwable {
		// 若此对象里next成员有值
		// 先执行用户的before()，再执行next.dealBefore()
		// 若next == null，则，执行invoke()
		// 执行after()
		if (false == interceptor.before(args)) {
			success = false;
			return null;
		}
		// 最后一次拦截中，若程序走到这说明前置拦截已全部成功
		// 当前置拦截出问题时，方法就不会被调用，那么就无需进行后置拦截
		Object result;
		if (next != null) {
			result = next.dealBefore(method, args, object);
			// 除最后一层拦截外，如何判断返回的null是由于前置拦截失败导致的还是方法返回值本身就是null?
			// 给success标记，就是为了判别null的来源
			if (next.isSuccess()) {
				result = interceptor.after(result);	
			}
		} else {
			// 前置拦截全部完成，则会执行下面的语句
			result = interceptor.after(invokeMethod(method, args, object));
		}

		return result;
	}

}
