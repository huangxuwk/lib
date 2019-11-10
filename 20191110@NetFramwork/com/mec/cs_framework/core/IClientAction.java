package com.mec.cs_framework.core;

public interface IClientAction {
	void serverAbnormalDrop();
	boolean confirmOffline();
	void beforeOffline();
	void outOfRoom();
	void privateConversation(String resourceId, String message);
	void publicConversation(String resourceId, String message);
	void serverForcedown();
	void beGoneByServer();
	void afterOffline();
}
