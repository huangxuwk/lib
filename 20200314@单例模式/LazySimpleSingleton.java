package com.dl.lazy_singleton;

/*
 * �ŵ㣺����˶�������ģʽ���ڴ��˷�����
 * ȱ�㣺�̲߳���ȫ
 */
public class LazySimpleSingleton {
	private static LazySimpleSingleton instance;
	
	// ���췽��˽�л�
	private LazySimpleSingleton() {
	}
	
	// �ṩһ��ȫ�ֵķ��ʵ�
	public synchronized static LazySimpleSingleton newInstance() {
		/*
		 * �̲߳���ȫ
		 * 1������ֵ��ͬ��˳��ִ�����ǰ�ȫ�ģ�����˳��ִ���򲻰�ȫ
		 * �������̶߳�ͨ����if�ж����������Ҷ�new��һ������ʱ����������߳���new����
		 * ���֮ǰ��û��return����ô�����̵߳õ��Ķ�����һ������Ϊ����new������߳�
		 * ������ǰһ���߳�new�Ķ�����Ȼ���濴��ͬһ�����󣬵�ʵ���̲߳�����ȫ
		 * 2������ֵ��ͬ
		 * ������������ͬ����ÿ���̶߳�ͨ����if�ж���������ÿ���߳�new����Ĺ�����return
		 * instance�Ĺ���û�б����ʱ����ôÿ���̵߳õ��Ķ����ֵ����ͬ
		 * 
		 * ����취������������
		 * ȱ�㣺�߲����£�����������������߳�
		 */
		if (null == instance) {
			instance = new LazySimpleSingleton();
		}
		
		return instance;
	}
}
