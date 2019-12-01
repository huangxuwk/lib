package com.dl.uddi.registry;

import java.util.List;

public class Customer {
	private List<String> services;
	private INetNode node;
	
	public Customer() {
	}

	public List<String> getServices() {
		return services;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}

	public INetNode getNode() {
		return node;
	}

	public void setNode(INetNode node) {
		this.node = node;
	}
	
}
