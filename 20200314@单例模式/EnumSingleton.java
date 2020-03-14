package com.dl.register_singleton;

/*
enum的实质如下，以map<String, Object>进行保存
map.put("INSTANCE", INSTANCE);

class EnumSingleton extends Enum {
    public static final EnumSingleton INSTANCE;
}

 * 枚举式单例模式的线程安全性由jdk来保证，jdk不允许反射创建枚举类型
 * 
 * 单元素的枚举类型已经成为实现单例的最佳方法。
 */
public enum EnumSingleton {
	INSTANCE;
	
	public static EnumSingleton getInstance() {
		return INSTANCE;
	}
}
