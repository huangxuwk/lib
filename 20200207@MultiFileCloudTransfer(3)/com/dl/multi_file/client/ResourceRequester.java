package com.dl.multi_file.client;

import com.dl.multi_file.strategy.INodeStrategy;
import com.dl.multi_file.strategy.IResourceStrategy;

/**
 * ��Դ������<br>
 * 1���������Ҫ�������Դ���н��ѡ����Լ���Դ������ԣ�<br>
 * 2�����ѡ����Ժ���Դ��������ǿ����õģ���ϵͳ����Ĭ�ϵģ�<br>
 * 3��������ɺ���з�������rpc������Դ�Ľ��գ�<br>
 * 4�����Ե�ʵ�ֺ���Դ�Ľ�����Ҫ�ŵ��������߳�����ɣ�
 * @author dl
 *
 */
public class ResourceRequester {
	private INodeStrategy nodeStrategy;
	private IResourceStrategy resourceStrategy;
	
	private IClientCenterAction centerAction;
	private IClientServerAction serverAction;
	
	public ResourceRequester() {
	}
	
	public void setCenterAction(IClientCenterAction centerAction) {
		this.centerAction = centerAction;
	}
	
	public void setServerAction(IClientServerAction serverAction) {
		this.serverAction = serverAction;
	}

	public INodeStrategy getNodeStrategy() {
		return nodeStrategy;
	}

	public IResourceStrategy getResourceStrategy() {
		return resourceStrategy;
	}
	
	
	
}
