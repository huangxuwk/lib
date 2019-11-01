package com.hx.cs_one2multi.client.test;

import com.hx.cs_one2multi.client.core.One2MultiClient;

public class TestForClient {
	
	public static void main(String[] args) {
		new One2MultiClient()
				.setIp("192.168.1.133")
				.setPort(54188)
				.talking();
	}
}
