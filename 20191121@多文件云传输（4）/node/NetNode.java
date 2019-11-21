package com.dl.mmfct.node;

public class NetNode implements INetNode {
	private String ip;
	private int port;
	private int type;
	private int sendTime;
	
	public NetNode() {
	}

	NetNode(String ip, int port, int type) {
		this.ip = ip;
		this.port = port;
		this.type = type;
	}

	@Override
	public String getIp() {
		return ip;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public int getSendTime() {
		return sendTime;
	}

	@Override
	public void increaseSendTime() {
		++sendTime;
	}

	void setIp(String ip) {
		this.ip = ip;
	}

	void setPort(int port) {
		this.port = port;
	}

	void setType(int type) {
		this.type = type;
	}

	void setSendTime(int sendTime) {
		this.sendTime = sendTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + port;
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
		NetNode other = (NetNode) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (port != other.port)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ip + "(" + port + ") " + (type == INetNode.SERVER ? "服务器" : "客户机");
	}
	
}
