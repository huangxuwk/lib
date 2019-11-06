package com.mec.cs_framework.core;

public class ClientActionAdapter implements IClientAction {

	public ClientActionAdapter() {
	}
	
	@Override
	public void refuseOnline(String reason) {
	}

	@Override
	public void afterConnectError() {
	}

	@Override
	public void successOnline() {
	}

	@Override
	public void serverAbnormalDrop() {
	}

	@Override
	public boolean confirmOffline() {
		return true;
	}

	@Override
	public void afterOffline() {
	}

	@Override
	public void dealResponse(String action, String result) {
	}

}
