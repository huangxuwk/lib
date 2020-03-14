package com.dl.lazy_singleton;

/*
 * 优点：解决了饿汉单例模式的内存浪费问题
 * 缺点：线程不安全
 */
public class LazySimpleSingleton {
	private static LazySimpleSingleton instance;
	
	// 构造方法私有化
	private LazySimpleSingleton() {
	}
	
	// 提供一个全局的访问点
	public synchronized static LazySimpleSingleton newInstance() {
		/*
		 * 线程不安全
		 * 1、返回值相同：顺序执行下是安全的，不是顺序执行则不安全
		 * 当两个线程都通过了if判断条件，并且都new了一个对象时，如果两个线程在new对象
		 * 完成之前都没有return，那么两个线程得到的对象将是一个，因为后者new对象的线程
		 * 覆盖了前一个线程new的对象，虽然表面看是同一个对象，但实则线程并不安全
		 * 2、返回值不同
		 * 与上面的情况不同，当每个线程都通过了if判断条件，且每个线程new对象的过程与return
		 * instance的过程没有被打断时，那么每个线程得到的对象的值都不同
		 * 
		 * 解决办法：给方法加锁
		 * 缺点：高并发下，会产生大量的阻塞线程
		 */
		if (null == instance) {
			instance = new LazySimpleSingleton();
		}
		
		return instance;
	}
}
