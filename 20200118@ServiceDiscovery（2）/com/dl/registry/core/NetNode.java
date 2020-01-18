package com.dl.registry.core;

public class NetNode {
	private String ip;
	private int port;
	
	public NetNode() {
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
	
	@Override
	public String toString() {
		return "ip: " + ip + " port: " + port;
	}
}
