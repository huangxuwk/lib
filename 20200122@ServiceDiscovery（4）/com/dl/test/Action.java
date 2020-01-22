package com.dl.test;

import java.util.List;

import com.dl.consumer.core.IAction;
import com.dl.registry.core.NetNode;
import com.dl.server.core.ProxyAnntotation;

@ProxyAnntotation(interfaces = { IAction.class })
public class Action implements IAction {

	@Override
	public List<String> updataServiceTags() {
		System.out.println("下载全部信息");
		return null;
	}

	@Override
	public List<NetNode> updataNews(String serviceTag) {
		
		return null;
	}

}
