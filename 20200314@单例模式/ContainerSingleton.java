package com.dl.register_singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * ע��ʽ����ģʽ������ʽ����ģʽ��ö��ʽ����ģʽ�����棨����->������
 * ö��ʽ�����е�map��jdk�����ƣ�������ʽ����ģʽ��map���û�����ά�����Եø����
 * ϵͳ�������ڶ���������������ʹ������ʽ����ģʽ���������еĵ�������
 * ��Spring ioc ��ʹ�õľ�������ģʽ
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
