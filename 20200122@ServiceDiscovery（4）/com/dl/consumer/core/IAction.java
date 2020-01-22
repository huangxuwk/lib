package com.dl.consumer.core;

import java.util.List;

import com.dl.registry.core.NetNode;

/**
 * 提供RPC远程方法调用的接口
 * 
 * @author dl
 *
 */
public interface IAction {
	List<String> updataServiceTags();
	List<NetNode> updataNews(String serviceTag);
}
