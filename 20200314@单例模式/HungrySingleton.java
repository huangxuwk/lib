package com.dl.hungry_singleton;

/*
 * �ŵ㣺�򵥣��׶����ܹ���֤���Ե��̰߳�ȫ
 * ȱ�㣺����ģʽ���˷ѿռ䣬�������ϵͳ������ģʽʹ�õĽ��٣����Խ���
 */
public class HungrySingleton {
	// ������ص�ʱ�򣬵���������Ѿ�������
	private static final HungrySingleton instance;
	
	static {
		instance = new HungrySingleton();
	}
	
	// ���췽��˽�л�
	private HungrySingleton() {
	}
	
	// �ṩһ��ȫ�ֵķ��ʵ�
	public static HungrySingleton newInstance() {
		return instance;
	}
}
