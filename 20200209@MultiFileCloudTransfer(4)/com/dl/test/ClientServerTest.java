package com.dl.test;

import java.util.List;

import com.dl.multi_file.client.RequestClient;
import com.dl.multi_file.client.ResourceRequester;
import com.dl.multi_file.netWork.NetNode;
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
		ResourceServer server = new ResourceServer();
		server.setLocalResources(local);
		try {
			server.registryResource(list);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		List<NetNode> nodeList = client.updataNews(list.get(0));
		System.out.println(nodeList.size());
		for (NetNode netNode : nodeList) {
			System.out.println(netNode);
		}
		ResourceRequester requester = new ResourceRequester(client, local);
		requester.requestResource(nodeList, list.get(0));
	}
}
