package com.dl.sd.registry;

import com.dl.rpc.server.RPCServer;

/**
 * ע�����ķ�����<br>
 * 1������ע�����ķ�������RPC��������<br>
 * 2������������⣬�����̳߳أ�<br>
 * 3��������������ip��port�������ã�����ʱ��Ҳ�����ã�<br>
 * 4���������������̣߳�������ѯ�̣߳�<br>
 * 5��֧�ֶ����еĿ�������йرգ�
 * 
 * @author dl
 *
 */
public class RegistrationCenter {
	private RPCServer rpcServer;
	
	public RegistrationCenter() {
		rpcServer = new RPCServer();
	}
	
	public void readConfig(String path) {
		rpcServer.readConfig(path);
	}
	
	public void startup() {
		rpcServer.startup();
	}
	
	public void shutdown() {
		rpcServer.shutdown();
	}

}
