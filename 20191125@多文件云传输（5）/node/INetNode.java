package com.dl.mmfct.node;

public interface INetNode {
	int SERVER = 1;
	int CLIENT = 0;
	
	String getIp();
	int getPort();
	int getType();
	int getSendTime();
	void increaseSendTime();
}
