package com.mec.uddi.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProviderPool {
	private static final Map<String, List<Provider>> providerPool 
			= new HashMap<String, List<Provider>>();
	
	public ProviderPool() {
	}
	
	public void registry(Provider provider) {
		String service = provider.getService();
		synchronized (ProviderPool.class) {
			List<Provider> providerList = providerPool.get(service);
			if (providerList == null) {
				providerList = new ArrayList<>();
				providerPool.put(service, providerList);
			}
			if (!providerList.contains(provider)) {
				providerList.add(provider);
			}
		}
	}
	
	public void logout(Provider provider) {
		String service = provider.getService();
		synchronized (ProviderPool.class) {
			List<Provider> providerList = providerPool.get(service);
			if (providerList == null) {
				return;
			}
			if (providerList.contains(provider)) {
				providerList.remove(provider);
				if (providerList.isEmpty()) {
					providerPool.remove(service);
				}
			}
		}
	}
	
}
