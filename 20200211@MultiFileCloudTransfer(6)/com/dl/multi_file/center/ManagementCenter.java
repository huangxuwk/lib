package com.dl.multi_file.center;

import com.dl.sd.registry.RegistrationCenter;

public class ManagementCenter {
	private RegistrationCenter registrationCenter;
	
	public ManagementCenter() {
		registrationCenter = new RegistrationCenter();
	}
	
	/**
	 * ��ȡָ��·���µ�rpc�����ļ�
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
