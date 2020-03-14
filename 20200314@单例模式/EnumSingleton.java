package com.dl.register_singleton;

/*
enum��ʵ�����£���map<String, Object>���б���
map.put("INSTANCE", INSTANCE);

class EnumSingleton extends Enum {
    public static final EnumSingleton INSTANCE;
}

 * ö��ʽ����ģʽ���̰߳�ȫ����jdk����֤��jdk�������䴴��ö������
 * 
 * ��Ԫ�ص�ö�������Ѿ���Ϊʵ�ֵ�������ѷ�����
 */
public enum EnumSingleton {
	INSTANCE;
	
	public static EnumSingleton getInstance() {
		return INSTANCE;
	}
}
