package com.dl.test;

import java.util.List;

import com.dl.multi_file.resource.LocalResources;
import com.dl.multi_file.server.ResourceServer;
import com.dl.rpc.server.RPCMethodFactory;

public class TServer {

	public static void main(String[] args) {
		RPCMethodFactory.scanPackage("com.dl");
		
		LocalResources local = new LocalResources();
		local.scanLocalResource();
		List<String> list = local.resourceList();
		for (String string : list) {
			System.out.println(string);
		}
		ResourceServer server = new ResourceServer();
		try {
			server.registryResource(list);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
