package com.dl.multi_file.client;

import com.dl.multi_file.strategy.INodeStrategy;
import com.dl.multi_file.strategy.IResourceStrategy;

/**
 * 资源请求者<br>
 * 1、此类对需要申请的资源进行结点选择策略及资源分配策略；<br>
 * 2、结点选择策略和资源分配策略是可设置的，且系统带有默认的；<br>
 * 3、策略完成后进行服务器的rpc请求及资源的接收；<br>
 * 4、策略的实现和资源的接收需要放到单独的线程中完成；
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
