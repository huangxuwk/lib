package com.dl.consumer.core;

import java.util.List;

import com.dl.registry.core.NetNode;

public interface IAction {
	List<String> updataServiceTags();
	List<NetNode> updataNews(String serviceTag);
}
