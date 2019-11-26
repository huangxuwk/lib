package com.dl.mmfct.resource;

import java.util.List;

import com.dl.mmfct.node.INetNode;

public interface IrmiResourceNodeRelation {
	void registyNode(INetNode node, List<ResourceBaseInfo> rbiList);
	void registyNode(INetNode node, ResourceBaseInfo rbi);
	void logoutNode(INetNode node);
}
