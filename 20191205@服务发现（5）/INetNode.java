package com.mec.uddi.registry;

public interface INetNode {
	int DEFAULT_REGISTRY_CENTER_PROT = 54188;
	
	void setIp(String ip);
	void setPort(int port);
	
	String getIp();
	int getPort();
}
