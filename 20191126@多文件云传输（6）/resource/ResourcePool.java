package com.dl.mmfct.resource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResourcePool {
	private static final Map<String, ResourceBaseInfo> rbiPool = new ConcurrentHashMap<>();
	
	public ResourcePool() {
	}
	
	public static boolean addResource(ResourceBaseInfo rbi) {
		String appName = rbi.getAppName();
		ResourceBaseInfo oldRbi = rbiPool.get(appName);
		if (oldRbi != null) {
			if (rbi.getVersion().equals(oldRbi.getVersion())) {
				return false;
			}
		}
		
		rbiPool.put(appName, rbi);
		
		return true;
	}
	
	public static ResourceBaseInfo getResourceBaseInfo(String appName) {
		return rbiPool.get(appName);
	}
}
