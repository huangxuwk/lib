package com.dl.sd.registry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.dl.multi_file.netWork.NetNode;
import com.util.ThreadPoolFactory;

/**
 * ���񻺴���<br>
 * 1����ע��ķ������ͷ����б���л��棻<br>
 * 2������Ϣ���浽�Է����Ϊ�����������б�Ϊֵ��map�У�<br>
 * 3��֧�ַ���ͷ���������ɾ��
 * 
 * @author dl
 *
 */
public class ServiceCache {
	private static final ConcurrentHashMap<String, List<NetNode>> netPool;
	
	static {
		netPool = new ConcurrentHashMap<>();
	}
	
	public void put(String registryTag, NetNode netNode) {
		List<NetNode> nodeList = netPool.get(registryTag);
		if (nodeList == null) {
			nodeList = new ArrayList<NetNode>();
			netPool.put(registryTag, nodeList);
		}
		if (!nodeList.contains(netNode)) {
			nodeList.add(netNode);	
		}
	}
	
	public void remove(String serviceTag, INetNode netNode) {
		new ThreadPoolFactory().execute(new Thread(new InnerRemove(serviceTag, netNode)));
	}
	
	public void remove(INetNode netNode) {
		new ThreadPoolFactory().execute(new Thread(new InnerRemove(null, netNode)));
	}
	
	public List<NetNode> getNodeList(String serviceTag) {
		return netPool.get(serviceTag);
	}
	
	public List<String> getAllServiceTags() {
		return	new ArrayList<>(netPool.keySet());
	}
	
	private class InnerRemove implements Runnable {
		private String serviceTag;
		private INetNode netNode;

		public InnerRemove(String serviceTag, INetNode netNode) {
			this.serviceTag = serviceTag;
			this.netNode = netNode;
		}	
		
		@Override
		public void run() {
			if (serviceTag != null) {
				List<NetNode> tagList = netPool.get(serviceTag);
				tagList.remove(netNode);
			} else {
				Set<?> set = netPool.keySet();
				Iterator<?> iterator = set.iterator();
				while (iterator.hasNext()) {
					String tag = (String) iterator.next();
					List<NetNode> nodeList = netPool.get(tag);
					if (nodeList != null) {
						nodeList.remove(netNode);
						if (nodeList.isEmpty()) {
							iterator.remove();
						}
					}
				}
			}
		}
		
	}
	
}
