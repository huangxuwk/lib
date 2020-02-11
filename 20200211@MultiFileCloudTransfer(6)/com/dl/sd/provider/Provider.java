package com.dl.sd.provider;

import java.util.List;

import com.dl.multi_file.netWork.NetNode;
import com.dl.rpc.client.RPCClient;
import com.dl.rpc.client.RPCClientProxy;

/**
 * 服务提供者<br>
 * 向注册中心注册/注销服务；<br>
 * 
 * @author dl
 *
 */
public class Provider {
	private RPCClient rpcClient;
	private IProviderAction action;
	
	private NetNode netNode;
	
	public Provider() {
		rpcClient = new RPCClient();
		RPCClientProxy clientProxy = new RPCClientProxy();
		clientProxy.setClient(rpcClient);
		action = clientProxy.jdkProxy(IProviderAction.class);
	}
	
	public void setNetNode(NetNode netNode) {
		this.netNode = netNode;
	}
	
	public void readConfig(String path) {
		rpcClient.readConfig(path);
	}
	
	/**
	 * 注册服务，由rpc负责，可能会发送失败；<br>
	 * 失败后告知上一层；
	 * @param serviceList
	 * @throws Throwable 
	 */
	public void registryService(List<String> serviceList) throws Throwable {
		action.registryService(serviceList, netNode);
	}
	
	/**
	 * 注销服务，由rpc负责，可能会发送失败；<br>
	 * 失败后告知上一层；
	 * @param serviceList
	 * @throws Throwable 
	 */
	public void cancellationService(List<String> serviceList) throws Throwable {
		action.cancellationService(serviceList, netNode);	
	}
	
}
