package com.dl.sd.registry;

import java.util.List;

import com.dl.multi_file.netWork.NetNode;
import com.dl.rpc.server.RPCProxyAnntotation;
import com.dl.sd.consumer.IConsumerAction;

@RPCProxyAnntotation(interfaces = { IConsumerAction.class })
public class ConsumerAction implements IConsumerAction {
	private ServiceCache serviceCache;
	
	public ConsumerAction() {
		serviceCache = new ServiceCache();
	}
	
	
	@Override
	public List<String> updataServiceTags() {
		return serviceCache.getAllServiceTags();
	}

	@Override
	public List<NetNode> updataNews(String serviceTag) {
		return serviceCache.getNodeList(serviceTag);
	}

}
