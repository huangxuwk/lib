package com.dl.registry.core;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceCache {
	private static final Map<String, List<NetNode>> netPool;
	
	static {
		netPool = new ConcurrentHashMap<>();
	}
	
	public List<NetNode> getNodeList(String serviceTag) {
		return netPool.get(serviceTag);
	}
	
	// TODO É¾³ý½áµã²ßÂÔ
}
