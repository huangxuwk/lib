package com.mec.uddi.registry;

public class NetNode implements INetNode {
	private String ip;
	private int port;
	
	public NetNode() {
	}
	
	@Override
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public void setPort(int port) {
		this.port = port;
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
	public String toString() {
		return ip + port; 
	}

}
