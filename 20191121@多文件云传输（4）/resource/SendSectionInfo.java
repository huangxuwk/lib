package com.dl.mmfct.resource;

import java.util.List;

import com.dl.mmfct.node.INetNode;
import com.dl.mmfct.section.SectionInfo;

public class SendSectionInfo {
	private INetNode receiveNode;
	private INetNode sendNode;
	private List<SectionInfo> sectionList;
	
	public SendSectionInfo() {
	}

	public INetNode getReceiveNode() {
		return receiveNode;
	}

	public void setReceiveNode(INetNode receiveNode) {
		this.receiveNode = receiveNode;
	}

	public INetNode getSendNode() {
		return sendNode;
	}

	public void setSendNode(INetNode sendNode) {
		this.sendNode = sendNode;
	}

	public List<SectionInfo> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<SectionInfo> sectionList) {
		this.sectionList = sectionList;
	}
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("∑¢ÀÕ∂À:").append(sendNode).append('\n')
			.append("Ω” ’∂À:").append(receiveNode);
		for (SectionInfo section : sectionList) {
			str.append("\n\t").append(section);
		}
		str.append('\n');
		
		return str.toString();
	}
}
