package com.hx.cs_framework.core;

/**
 * �۲���ģʽ
 * ���۽�Ҫʵ�ַ������Ľ�����ʲô���͵�
 * ������ͨ������ӿ���ʵ��
 * ��������������淴����Ϣ�����Է������κ�
 * ���߱�д�߲�֪���Ľ�����
 * ��Ҫʹ�����ģʽ������Ҫ������һ���ӿ�
 * INetListener, ������ӿ��а���һ������
 * ���������Ŀ�ĵ�һ���������û��������ĸ�������
 * ������Ϣ��������������ǳ��󷽷��������û�ʹ��
 * �ͱ�����ʵ��������ô�ʹﵽ�˹۲���ģʽ��Ŀ�ġ�
 * @author chaojidalao
 *
 */
/*
 * ˵���ߣ���������Ϣ�ģ�����Ҫ��������Ϣ������������
 */
public interface INetSpeaker {
	void addListener(INetListener listener);
	void removeListener(INetListener listener);
}
