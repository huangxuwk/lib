package com.dl.lazy_singleton;

/*
 * 双重检查锁饿汉单例模式
 * 
 * 优点：解决了线程安全问题，也解决了性能问题
 * 缺点：可读性差，代码不够优雅，双if
 */
public class LazyDoubleCheckSingleton {
	private volatile static LazyDoubleCheckSingleton instance;
	
	private LazyDoubleCheckSingleton() {
	}
	
	public synchronized static LazyDoubleCheckSingleton newInstance() {
		// 外层检查，控制阻塞的条件，用来提高性能
		if (null == instance) {
			synchronized(LazyDoubleCheckSingleton.class) {
				// 内层检查，控制创建实例的条件
				if (null == instance) {
					instance = new LazyDoubleCheckSingleton();	
				}
			}
		}
		
		return instance;
	}
}
