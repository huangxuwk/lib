package com.mec.uddi.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProviderAddressPool {
	private static final Map<String, List<INetNode>> nodePool
			= new HashMap<String, List<INetNode>>();
	
	public ProviderAddressPool() {
	}

	public void addProvider(Provider provider) {
		String service = provider.getService();
		synchronized (nodePool) {
			List<INetNode> nodeList = nodePool.get(service);
			if (nodeList == null) {
				nodeList = new ArrayList<INetNode>();
				nodePool.put(service, nodeList);
			}
			nodeList.add(provider.getNode());
		}
	}
	
	public void removeProvider(Provider provider) {
		String service = provider.getService();
		INetNode netNode = provider.getNode();
		synchronized (nodePool) {
			List<INetNode> nodeList = nodePool.get(service);
			if (nodeList == null) {
				return;
			}
			if (nodeList.contains(netNode)) {
				nodeList.remove(netNode);
				if (nodeList.isEmpty()) {
					nodePool.remove(service);
				}
			}
		}
	}
	
	public List<INetNode> getProviderAddressList(String service) {
		return nodePool.get(service);
	}
	
}
