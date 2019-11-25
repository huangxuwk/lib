package com.dl.mmfct.send;

import com.dl.mmfct.node.INetNode;
import com.dl.mmfct.resource.ResourceBaseInfo;

public interface IResourceSender {
	void sendSectionInfo(INetNode receiver, ResourceBaseInfo rbi);
}
