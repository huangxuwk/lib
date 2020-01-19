package com.dl.consumer.core;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.dl.registry.core.NetNode;

public class ClientCache {
	private static final Map<String, List<NetNode>> netPool = new ConcurrentHashMap<>();
	
	public ClientCache() {
	}
	
	public void addServiceTags(List<String> serviceTags) {
		netPool.clear();
		for (String string : serviceTags) {
			netPool.put(string, null);
		}
	}
	
	public void addService(String serviceTag, List<NetNode> nodeList) {
		netPool.put(serviceTag, nodeList);
	}
	
	public List<NetNode> getServerList(String serviceTag) {
		return netPool.get(serviceTag);
	}
	
	public Set<String> keySet() {
		return netPool.keySet();
	}
}
