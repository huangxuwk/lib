package com.dl.threadLocal_singleton;

/*
 * 在高并发下，懒汉模式创建单例对象是最容易出现线程安全性问题的地方
 * 而加锁在高并发情况下，性能较对很低，因此ThreadLocal用空间换时间
 * 也是一种很好地选择
 * 缺点：仍然无法保证反射创建对象
 */
public class ThreadLocalSingleton {
	private static final ThreadLocal<ThreadLocalSingleton> threadLocal = 
			new ThreadLocal<ThreadLocalSingleton>() {
				@Override
				public ThreadLocalSingleton get() {
					return new ThreadLocalSingleton();
				}; 
	};
	
	private ThreadLocalSingleton() {
	}
	
	public ThreadLocalSingleton getInstance() {
		return threadLocal.get();
	}
}
