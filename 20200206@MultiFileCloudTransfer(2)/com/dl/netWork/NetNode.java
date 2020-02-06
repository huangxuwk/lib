package com.dl.netWork;

public class NetNode {
	private String ip;
	private int port;
	private int sendCount;
	private int sendingCount;
	
	public NetNode() {
	}

	public NetNode(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	public int getSendingCount() {
		return sendingCount;
	}

	public void setSendingCount(int sendingCount) {
		this.sendingCount = sendingCount;
	}
	
	public void accomplishTask() {
		this.sendCount++;
		this.sendingCount--;
	}

	public void addTask() {
		this.sendingCount++;
	}
	
	@Override
	public String toString() {
		return "ip: " + ip + "port: " + port;
	}
}
