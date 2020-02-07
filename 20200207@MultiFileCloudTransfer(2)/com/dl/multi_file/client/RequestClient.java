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
	 * 提供下载资源管理中心全部资源句柄列表的方法
	 * @return
	 */
	public List<String> updataResourceHandles() {
		return consumer.updataServiceTags();
	}

	/**
	 * 提供根据资源句柄下载对应结点列表的方法
	 * @param resourceName 资源句柄
	 * @return
	 */
	public List<INetNode> updataNews(String resourceHandle) {
		return consumer.updataNews(resourceHandle);
	}
	
}
