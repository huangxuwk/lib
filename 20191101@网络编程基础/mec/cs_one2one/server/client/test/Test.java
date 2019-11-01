package com.mec.cs_one2one.server.client.test;

import com.mec.cs_one2one.server.client.core.One2OneClient;

public class Test {

	public static void main(String[] args) {
		new One2OneClient().setIp("192.168.1.133").setPort(54188).connectToServer();
	}

}
