package com.dl.sd.consumer;

import java.util.List;

import com.dl.multi_file.netWork.NetNode;

/**
 * �ṩRPCԶ�̷������õĽӿ�
 * 
 * @author dl
 *
 */
public interface IConsumerAction {
	List<String> updataServiceTags();
	List<NetNode> updataNews(String serviceTag);
}
