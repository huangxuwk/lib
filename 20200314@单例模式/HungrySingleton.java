package com.dl.hungry_singleton;

/*
 * 优点：简单，易读，能够保证绝对的线程安全
 * 缺点：饿汉模式会浪费空间，如果整个系统中这种模式使用的较少，可以接受
 */
public class HungrySingleton {
	// 在类加载的时候，单例对象就已经被创建
	private static final HungrySingleton instance;
	
	static {
		instance = new HungrySingleton();
	}
	
	// 构造方法私有化
	private HungrySingleton() {
	}
	
	// 提供一个全局的访问点
	public static HungrySingleton newInstance() {
		return instance;
	}
}
