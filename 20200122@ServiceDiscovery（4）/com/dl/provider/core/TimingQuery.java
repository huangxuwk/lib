package com.dl.provider.core;

/**
 * �ӿ�ʵ����<br>
 * ��ע������崻��󣬽�ʹ�ñ���ȥ��������ע�����ģ�
 * @author dl
 *
 */
public class TimingQuery implements ITimingQuery {

	@Override
	public void dealTimingQuery(Provider provider) {
		provider.startup();
	}
	
}
