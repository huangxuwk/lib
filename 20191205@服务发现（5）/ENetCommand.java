package com.mec.uddi.communication;

public enum ENetCommand {
	/**
	 * 服务提供者注册服务
	 */
	SERVICE_REGISTRY,
	/**
	 * 服务提供者注销服务
	 */
	SERVICE_LOGOUT,
	/**
	 * 服务消费者请求服务地址列表
	 */
	REQUEST_SERVICE_ADDRESS_LIST,
	/**
	 * 通知服务提供者注销服务
	 */
	ADVISE_SERVICE_LOGOUT,
	/**
	 * 通知服务提供者注册服务
	 */
	ADVISE_SERVICE_REGISTRY,
}
