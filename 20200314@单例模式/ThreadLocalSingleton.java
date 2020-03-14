package com.dl.threadLocal_singleton;

/*
 * �ڸ߲����£�����ģʽ�������������������׳����̰߳�ȫ������ĵط�
 * �������ڸ߲�������£����ܽ϶Ժܵͣ����ThreadLocal�ÿռ任ʱ��
 * Ҳ��һ�ֺܺõ�ѡ��
 * ȱ�㣺��Ȼ�޷���֤���䴴������
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
