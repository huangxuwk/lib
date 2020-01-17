package com.dl.consumer.core;

import java.util.List;

import com.dl.client.core.ClientProxy;
import com.dl.client.core.RPCClient;
import com.dl.registry.core.NetNode;
import com.util.ThreadPoolFactory;

/**
 * 服务消费者<br>
 * 1、上线即向注册中心申请全部服务列表；<br>
 * 2、定时从注册中心更新服务列表和对应服务器列表；<br>
 * 3、通过注册中心给的服务器列表进行负载均衡；<br>
 * 4、注册中心宕机，采取定时重连的方式，直到连接成功为止；
 * 
 * @author chaojidalao
 *
 */
public class Consumer implements Runnable {
	private RPCClient rpcClient;
	private ClientCache cache;
	
	private IAction action;
	private INodeStrategy strategy;
	
	public Consumer() {
		rpcClient = new RPCClient();
		action = new ClientProxy().jdkProxy(IAction.class);
		cache = new ClientCache();
	}
	
	public void setStrategy(INodeStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void loadPath(String path) {
		rpcClient.readConfig(path);
	}
	
	public void startup() {
		try {
			action.updataServiceTags();
		} catch(Exception e) {
			// TODO 通信中断，暂停定时更新
			ThreadPoolFactory.execute(new Thread(new InnerReConnect()));
		}
	}
	
	// 结点选择策略
	public NetNode getServer(String serviceTag) {
		List<NetNode> nodeList = cache.getServerList(serviceTag);
		if (nodeList == null) {
			action.updataNews(serviceTag);
		}
		return strategy.ServerBalance(nodeList);
	}
	
	@Override
	public void run() {
		// TODO 定时更新表
	}
	
	/**
	 * 开启线程处理对端的消息
	 * @author dl
	 *
	 */
	private class InnerReConnect implements Runnable {
		public InnerReConnect() {
		}

		@Override
		public void run() {
			// TODO 定时连接线程
		}
	}

}
