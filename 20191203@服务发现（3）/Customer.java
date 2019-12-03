package com.mec.uddi.registry;

public class Customer {
	private String service;
	private INetNode node;
	
	public Customer() {
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public INetNode getNode() {
		return node;
	}

	public void setNode(INetNode node) {
		this.node = node;
	}

	@Override
	public String toString() {
		return "[" + service + "//" + node + "]";
	}
	
}
