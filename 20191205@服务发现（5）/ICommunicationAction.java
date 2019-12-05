package com.mec.uddi.communication;

import com.mec.uddi.registry.CommunicationNode;

public interface ICommunicationAction {
	void dealNetMessage(CommunicationNode node, NetMessage netMessage);
	void peerAbnormalDrop(CommunicationNode node);
	void removeUnknowNode(CommunicationNode node);
}
