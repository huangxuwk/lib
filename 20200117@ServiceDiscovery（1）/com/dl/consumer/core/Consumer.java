package com.dl.consumer.core;

import java.util.List;

import com.dl.client.core.ClientProxy;
import com.dl.client.core.RPCClient;
import com.dl.registry.core.NetNode;
import com.util.ThreadPoolFactory;

/**
 * ����������<br>
 * 1�����߼���ע����������ȫ�������б�<br>
 * 2����ʱ��ע�����ĸ��·����б�Ͷ�Ӧ�������б�<br>
 * 3��ͨ��ע�����ĸ��ķ������б���и��ؾ��⣻<br>
 * 4��ע������崻�����ȡ��ʱ�����ķ�ʽ��ֱ�����ӳɹ�Ϊֹ��
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
			// TODO ͨ���жϣ���ͣ��ʱ����
			ThreadPoolFactory.execute(new Thread(new InnerReConnect()));
		}
	}
	
	// ���ѡ�����
	public NetNode getServer(String serviceTag) {
		List<NetNode> nodeList = cache.getServerList(serviceTag);
		if (nodeList == null) {
			action.updataNews(serviceTag);
		}
		return strategy.ServerBalance(nodeList);
	}
	
	@Override
	public void run() {
		// TODO ��ʱ���±�
	}
	
	/**
	 * �����̴߳���Զ˵���Ϣ
	 * @author dl
	 *
	 */
	private class InnerReConnect implements Runnable {
		public InnerReConnect() {
		}

		@Override
		public void run() {
			// TODO ��ʱ�����߳�
		}
	}

}
