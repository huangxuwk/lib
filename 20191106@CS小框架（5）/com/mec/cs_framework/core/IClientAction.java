package com.mec.cs_framework.core;

public interface IClientAction {
	void refuseOnline(String reason);
	void afterConnectError();
	void successOnline();
	void serverAbnormalDrop();
	boolean confirmOffline();
	void afterOffline();
	void dealResponse(String action, String result);
}
