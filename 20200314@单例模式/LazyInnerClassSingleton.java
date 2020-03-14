package com.dl.lazy_singleton;

/*
 * 静态内部类的特性：
 * 静态内部类其实和外部类的静态变量，静态方法一样，当被调用时都会加载外部类
 * 当只调用外部类的静态变量，静态方法时，静态内部类不会被加载
 * 当调用静态内部类中的属性和方法时会加载静态内部类
 * 
 * 优点：保证线程安全，因为类加载是线程安全的
 * 
 * 这是前四种方法中的最优解，但是都有一个共同的缺陷，反射机制可以破坏单例
 * 解决办法：构造方法中抛异常，但代码很不优雅
 */
public class LazyInnerClassSingleton {
	
	private LazyInnerClassSingleton() {
		if (LazyHolder.Lazy  != null) {
			// 枚举类自带这个功能（JDK不允许反射创建），因此出现了枚举式单例模式
			throw new RuntimeException("不允许反射创建对象！");
		}
	}
	
	// 这里的方法是静态方法，因此内部类必须是静态内部类
	public static LazyInnerClassSingleton getInstance() {
		return LazyHolder.Lazy;
	}
	
	public static class LazyHolder {
		private static final LazyInnerClassSingleton Lazy = new LazyInnerClassSingleton();
	}
}
