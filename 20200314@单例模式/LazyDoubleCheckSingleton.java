package com.dl.lazy_singleton;

/*
 * ˫�ؼ������������ģʽ
 * 
 * �ŵ㣺������̰߳�ȫ���⣬Ҳ�������������
 * ȱ�㣺�ɶ��Բ���벻�����ţ�˫if
 */
public class LazyDoubleCheckSingleton {
	private volatile static LazyDoubleCheckSingleton instance;
	
	private LazyDoubleCheckSingleton() {
	}
	
	public synchronized static LazyDoubleCheckSingleton newInstance() {
		// ����飬���������������������������
		if (null == instance) {
			synchronized(LazyDoubleCheckSingleton.class) {
				// �ڲ��飬���ƴ���ʵ��������
				if (null == instance) {
					instance = new LazyDoubleCheckSingleton();	
				}
			}
		}
		
		return instance;
	}
}
