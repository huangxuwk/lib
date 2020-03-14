package com.dl.register_singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * 注册式单例模式：容器式单例模式，枚举式单例模式升级版（饿汉->懒汉）
 * 枚举式单例中的map由jdk来控制，而容器式单例模式的map由用户类来维护，显得更灵活
 * 系统中若存在多个单例对象，则可以使用容器式单例模式来保存所有的单例对象
 * 在Spring ioc 中使用的就是这种模式
 */
public class ContainerSingleton {
	private static Map<String, Object> ioc = new ConcurrentHashMap<>();
	
	private ContainerSingleton() {
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(String className) {
		if (!ioc.containsKey(className)) {
			Object obj = null;
			try {
				obj = Class.forName(className);
				ioc.put(className, obj);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return (T) obj;
		} else {
			return (T) ioc.get(className);
		}
	}
}
