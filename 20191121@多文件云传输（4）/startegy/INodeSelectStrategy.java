package com.dl.mmfct.startegy;

import java.util.List;

import com.dl.mmfct.node.INetNode;
import com.dl.mmfct.resource.ResourceBaseInfo;

public interface INodeSelectStrategy {
	int DEFAULT_MAX_SENDER_COUNT = 5;
	
	void setMaxSenderCount(int maxSenderCount);
	void setServerDoSend(boolean serverDoSend);
	List<INetNode> selectNodeList(ResourceBaseInfo rbi);

}
