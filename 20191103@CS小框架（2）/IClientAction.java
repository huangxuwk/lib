package com.hx.cs_framework.core;

public interface IClientAction {
	void refuseOnline(String reason);
	void afterConnectError();
	void successOnline();
}
