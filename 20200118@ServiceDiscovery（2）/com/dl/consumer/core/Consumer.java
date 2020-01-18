package com.dl.consumer.core;

import java.util.List;

import com.dl.client.core.ClientProxy;
import com.dl.client.core.RPCClient;
import com.dl.registry.core.NetNode;
import com.timer.util.Timer;
import com.timer.util.UserAction;

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
	private Timer timer;
	
	private IAction action;
	private INodeStrategy strategy;
	
	public Consumer() {
		rpcClient = new RPCClient();
		ClientProxy clientProxy = new ClientProxy();
		clientProxy.setClient(rpcClient);
		action = clientProxy.jdkProxy(IAction.class);
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
			cache.addServiceTags(action.updataServiceTags());
		} catch(Exception e) {
			// 通信中断，定时更新起到了定时连接的功能
		}
	}
	
	public void shutdown() {
		if (timer != null) {
			timer.stopThread();
		}
	}
	
	// 结点选择策略
	public NetNode getServer(String serviceTag) {
		List<NetNode> nodeList = cache.getServerList(serviceTag);
		if (nodeList == null) {
			nodeList = action.updataNews(serviceTag);
			cache.addService(serviceTag, nodeList);
		}
		return strategy.ServerBalance(this, nodeList);
	}
	
	@Override
	public void run() {
		// 定时更新表
		try {
			Timer timer = new Timer();
			// 30min
			timer.setDelayTime(60*60*60*1000);
			timer.setUserAction(new UserAction() {
				@Override
				public void userAction() {
					List<String> tagList = action.updataServiceTags();
					cache.addServiceTags(tagList);
					List<NetNode> nodeList;
					for (String serviceTag : tagList) {
						nodeList = action.updataNews(serviceTag);
						cache.addService(serviceTag, nodeList);
					}
				}
			});
			timer.startThread();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
