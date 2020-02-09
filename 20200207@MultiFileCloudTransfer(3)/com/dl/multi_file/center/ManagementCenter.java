package com.dl.multi_file.center;

import com.dl.sd.registry.RegistrationCenter;

public class ManagementCenter {
	private RegistrationCenter registrationCenter;
	
	public ManagementCenter() {
		registrationCenter = new RegistrationCenter();
	}
	
	/**
	 * 读取指定路径下的rpc配置文件
	 * @param path
	 */
	public void readRPCConfig(String path) {
		registrationCenter.readConfig(path);
	}
	
	public void startup() {
		registrationCenter.startup();
	}
	
	public void shutdown() {
		registrationCenter.shutdown();
	}
	
}
