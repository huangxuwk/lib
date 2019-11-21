package com.dl.mmfct.startegy;

import java.util.List;

import com.dl.mmfct.node.INetNode;
import com.dl.mmfct.resource.SendSectionInfo;
import com.dl.mmfct.section.SectionInfo;

public interface IResourceAllocationStrategy {
	int DEFAULT_MAX_SECTION_LENGTH = 1 << 22;
	
	void setMaxSectionLength(int maxSectionLength);
	List<SendSectionInfo> allocationResource(
			INetNode receiveNode, List<SectionInfo> orgResource, List<INetNode> sendNodeList);
	List<SectionInfo>[] allocationResource(List<SectionInfo> orgResource, int sendCount);
}
