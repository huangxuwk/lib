package com.dl.sd.consumer;

import java.util.List;

import com.dl.rpc.client.RPCClientProxy;
import com.dl.multi_file.netWork.NetNode;
import com.dl.rpc.client.RPCClient;
import com.dl.sd.registry.INetNode;
import com.parser_reflect.util.PropertiesParser;
import com.timer.util.Timer;
import com.timer.util.UserAction;

/**
 * 服务消费者<br>
 * 1、上线即向注册中心申请全部服务列表；<br>
 * 2、定时从注册中心更新服务列表和对应服务器列表；<br>
 * 3、通过注册中心给的服务器列表进行负载均衡；<br>
 * 4、注册中心宕机，采取定时重连的方式，直到连接成功为止；
 * 
 * @author dl
 *
 */
public class Consumer implements Runnable {
	private RPCClient rpcClient;
	private ClientCache cache;
	
	private Timer timer;
	private int delayTime;
	
	private IConsumerAction action;
	private INodeStrategy strategy;
	
	public Consumer() {
		rpcClient = new RPCClient();
		RPCClientProxy clientProxy = new RPCClientProxy();
		clientProxy.setClient(rpcClient);
		action = clientProxy.jdkProxy(IConsumerAction.class);
		cache = new ClientCache();
	}
	
	public void setStrategy(INodeStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void readConfig(String path) {
		rpcClient.readConfig(path);
		PropertiesParser.load(path);
		
		String delayTimeStr = PropertiesParser.findElement("delayTime");
		try {
			if (delayTimeStr != null && !delayTimeStr.equals("")) {
				int delayTime = Integer.valueOf(delayTimeStr);
				if (delayTime > 0 && delayTime < 65536) {
					this.delayTime = delayTime;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public INetNode getServer(String serviceTag) {
		List<NetNode> nodeList = cache.getServerList(serviceTag);
		if (nodeList == null) {
			nodeList = action.updataNews(serviceTag);
			cache.addService(serviceTag, nodeList);
		}
		return strategy.ServerBalance(this, serviceTag, nodeList);
	}
	
	public List<String> updataServiceTags() {
		List<String> serviceTags = action.updataServiceTags();
		cache.addServiceTags(serviceTags);
		return serviceTags;
	}

	public List<NetNode> updataNews(String serviceTag) {
		List<NetNode> nodeList = action.updataNews(serviceTag);
		cache.addService(serviceTag, nodeList);
		return nodeList;
	}
	
	@Override
	public void run() {
		// 定时更新表
		try {
			timer = new Timer();
			if (delayTime > 0) {
				timer.setDelayTime(delayTime);
			}
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
