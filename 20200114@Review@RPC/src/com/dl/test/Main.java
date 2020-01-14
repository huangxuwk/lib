package com.dl.test;

import com.dl.client.core.ClientProxy;
import com.dl.client.core.RPCClient;
import com.dl.server.core.MethodFactory;
import com.dl.server.core.RPCServer;

public class Main {

	public static void main(String[] args) {
		MethodFactory.scanPackage("com.dl.test");
		
		RPCServer server = new RPCServer();
		server.setPort(55555);
		server.startup();
		
		RPCClient client = new RPCClient();
		client.setPort(55555);
		client.setIp("192.168.1.74");
		ClientProxy proxy = new ClientProxy();
		proxy.setClient(client);
		TestInter test = proxy.jdkProxy(Test.class);
		test.show("");
	}
}
