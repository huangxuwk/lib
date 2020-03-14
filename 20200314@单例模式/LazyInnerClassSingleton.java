package com.dl.lazy_singleton;

/*
 * ��̬�ڲ�������ԣ�
 * ��̬�ڲ�����ʵ���ⲿ��ľ�̬��������̬����һ������������ʱ��������ⲿ��
 * ��ֻ�����ⲿ��ľ�̬��������̬����ʱ����̬�ڲ��಻�ᱻ����
 * �����þ�̬�ڲ����е����Ժͷ���ʱ����ؾ�̬�ڲ���
 * 
 * �ŵ㣺��֤�̰߳�ȫ����Ϊ��������̰߳�ȫ��
 * 
 * ����ǰ���ַ����е����Ž⣬���Ƕ���һ����ͬ��ȱ�ݣ�������ƿ����ƻ�����
 * ����취�����췽�������쳣��������ܲ�����
 */
public class LazyInnerClassSingleton {
	
	private LazyInnerClassSingleton() {
		if (LazyHolder.Lazy  != null) {
			// ö�����Դ�������ܣ�JDK�������䴴��������˳�����ö��ʽ����ģʽ
			throw new RuntimeException("�������䴴������");
		}
	}
	
	// ����ķ����Ǿ�̬����������ڲ�������Ǿ�̬�ڲ���
	public static LazyInnerClassSingleton getInstance() {
		return LazyHolder.Lazy;
	}
	
	public static class LazyHolder {
		private static final LazyInnerClassSingleton Lazy = new LazyInnerClassSingleton();
	}
}
