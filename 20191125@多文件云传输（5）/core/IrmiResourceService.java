package com.dl.mmfct.core;

import com.dl.mmfct.node.INetNode;
import com.dl.mmfct.resource.ResourceBaseInfo;

public interface IrmiResourceService {
	void increaseSendTime(INetNode node);
	int requestResource(INetNode receiver, ResourceBaseInfo rbi);
	void startReceive();
}
