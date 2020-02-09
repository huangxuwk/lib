package com.dl.test;

import java.util.List;

import com.dl.multi_file.client.RequestClient;
import com.dl.multi_file.resource.LocalResources;
import com.dl.multi_file.server.ResourceServer;

public class ClientServerTest {
	
	public static void main(String[] args) {
		LocalResources local = new LocalResources();
		local.scanLocalResource();
		List<String> list = local.resourceList();
		for (String string : list) {
			System.out.println(string);
		}
		RequestClient client = new RequestClient();
		client.setLocalResources(local);
		ResourceServer server = new ResourceServer();
		server.setLocalResources(local);
		try {
			server.registryResource(list);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
