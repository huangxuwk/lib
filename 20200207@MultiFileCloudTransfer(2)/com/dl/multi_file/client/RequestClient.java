package com.dl.multi_file.client;

import java.util.List;

import com.dl.multi_file.resource.LocalResources;
import com.dl.sd.consumer.Consumer;
import com.dl.sd.registry.INetNode;

public class RequestClient {
	private Consumer consumer;
	
	private LocalResources localResources;
	
	public RequestClient() {
		consumer = new Consumer();
	}
	
	public void setLocalResources(LocalResources localResources) {
		this.localResources = localResources;
	}

	/**
	 * �ṩ������Դ��������ȫ����Դ����б�ķ���
	 * @return
	 */
	public List<String> updataResourceHandles() {
		return consumer.updataServiceTags();
	}

	/**
	 * �ṩ������Դ������ض�Ӧ����б�ķ���
	 * @param resourceName ��Դ���
	 * @return
	 */
	public List<INetNode> updataNews(String resourceHandle) {
		return consumer.updataNews(resourceHandle);
	}
	
}
