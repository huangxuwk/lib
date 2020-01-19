package com.dl.consumer.core;

import java.util.List;

import com.dl.registry.core.NetNode;

public interface INodeStrategy {
	NetNode ServerBalance(Consumer consumer, List<NetNode> nodeList);
}
