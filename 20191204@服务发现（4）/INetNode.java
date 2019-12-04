package com.mec.uddi.registry;

public interface INetNode {
	void setId(String id);
	void setIp(String ip);
	void setPort(int port);
	
	String getId();
	String getIp();
	int getPort();
}
