package com.dl.mmfct.startegy;

import java.util.ArrayList;
import java.util.List;

import com.dl.mmfct.node.INetNode;
import com.dl.mmfct.resource.SendSectionInfo;
import com.dl.mmfct.section.SectionInfo;

public class ResourceAllocationStrategy implements IResourceAllocationStrategy {
	private int maxSectionLength;
		
	public ResourceAllocationStrategy() {
		this.maxSectionLength = DEFAULT_MAX_SECTION_LENGTH;
	}
	
	@Override
	public void setMaxSectionLength(int maxSectionLength) {
		this.maxSectionLength = maxSectionLength;
	}

	@Override
	public List<SendSectionInfo> allocationResource(INetNode receiveNode, List<SectionInfo> orgResource,
			List<INetNode> sendNodeList) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SectionInfo>[] allocationResource(List<SectionInfo> orgResource, int sendCount) {
		if (sendCount <= 0) {
			return null;
		}
		
		Object[] result = new Object[sendCount];
		for (int index = 0; index < result.length; index++) {
			result[index] = new ArrayList<SectionInfo>();
		}
		
		int index = 0;
		for (SectionInfo section : orgResource) {
			int sectionSize = section.getSize();
			List<SectionInfo> sectionList = (List<SectionInfo>) result[index];
			if (sectionSize <= maxSectionLength) {
				sectionList.add(new SectionInfo(section));
				index = (index + 1) % sendCount;
				continue;
			}
			Long offset = 0L;
			int restLen = sectionSize;
			int len;
			while (restLen > 0) {
				len = restLen > maxSectionLength ? maxSectionLength : restLen;
				sectionList.add(new SectionInfo(section.getFileHandle()
						, offset + section.getOffset(), len));
				offset += len;
				restLen -= len;
				index = (index + 1) % sendCount;
			}
		}
			
		return (List<SectionInfo>[]) result;
	}

}
