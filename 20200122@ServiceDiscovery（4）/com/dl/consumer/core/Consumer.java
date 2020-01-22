package com.dl.consumer.core;

import java.util.List;

import com.dl.client.core.ClientProxy;
import com.dl.client.core.RPCClient;
import com.dl.registry.core.NetNode;
import com.parser_reflect.util.PropertiesParser;
import com.timer.util.Timer;
import com.timer.util.UserAction;

/**
 * ����������<br>
 * 1�����߼���ע����������ȫ�������б�<br>
 * 2����ʱ��ע�����ĸ��·����б�Ͷ�Ӧ�������б�<br>
 * 3��ͨ��ע�����ĸ��ķ������б���и��ؾ��⣻<br>
 * 4��ע������崻�����ȡ��ʱ�����ķ�ʽ��ֱ�����ӳɹ�Ϊֹ��
 * 
 * @author dl
 *
 */
public class Consumer implements Runnable {
	private RPCClient rpcClient;
	private ClientCache cache;
	
	private Timer timer;
	private int delayTime;
	
	private IAction action;
	private INodeStrategy strategy;
	
	public Consumer() {
		rpcClient = new RPCClient();
		ClientProxy clientProxy = new ClientProxy();
		clientProxy.setClient(rpcClient);
		action = clientProxy.jdkProxy(IAction.class);
		cache = new ClientCache();
		readConfig("/SDConfig.properties");
	}
	
	public void setStrategy(INodeStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void readConfig(String path) {
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
			// ͨ���жϣ���ʱ�������˶�ʱ���ӵĹ���
		}
	}
	
	public void shutdown() {
		if (timer != null) {
			timer.stopThread();
		}
	}
	
	// ���ѡ�����
	public NetNode getServer(String serviceTag) {
		List<NetNode> nodeList = cache.getServerList(serviceTag);
		if (nodeList == null) {
			nodeList = action.updataNews(serviceTag);
			cache.addService(serviceTag, nodeList);
		}
		return strategy.ServerBalance(this, serviceTag, nodeList);
	}
	
	@Override
	public void run() {
		// ��ʱ���±�
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
