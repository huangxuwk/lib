package com.dl.uddi.registry;

public class Provider {
	private String service;
	private INetNode node;
	
	public Provider() {
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public void setNode(INetNode node) {
		this.node = node;
	}

	public INetNode getNode() {
		return node;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provider other = (Provider) obj;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		return true;
	}
	
}
