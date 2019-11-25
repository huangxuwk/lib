package com.dl.mmfct.startegy;

import java.util.ArrayList;
import java.util.List;

import com.dl.mmfct.node.INetNode;
import com.dl.mmfct.resource.ResourceBaseInfo;
import com.dl.mmfct.resource.ResourceNodeRelation;

public class NodeSelectStartegy implements INodeSelectStrategy {
	private int maxSenderCount;
	private boolean serverDoSend;
	
	public NodeSelectStartegy() {
		this.maxSenderCount = DEFAULT_MAX_SENDER_COUNT;
		this.serverDoSend = true;
	}
	
	@Override
	public void setMaxSenderCount(int maxSenderCount) {
		this.maxSenderCount = maxSenderCount;
	}

	@Override
	public void setServerDoSend(boolean serverDoSend) {
		this.serverDoSend = serverDoSend;
	}
	
	@Override
	public List<INetNode> selectNodeList(ResourceBaseInfo rbi) {
		ResourceNodeRelation relation = new ResourceNodeRelation();
		
		List<INetNode> nodeList = relation.getNodeListByResource(rbi);
		int senderCount = nodeList.size();
		if (senderCount <= 1) {
			return nodeList;
		}
		if (!serverDoSend) {
			List<INetNode> serverDontSendNodeList = new ArrayList<INetNode>();
			serverDontSendNodeList.addAll(nodeList);
			for (INetNode node : nodeList) {
				if (node.getType() == INetNode.SERVER) {
					serverDontSendNodeList.remove(node);
				}
			}
			nodeList = serverDontSendNodeList;
		}
		senderCount = nodeList.size();
		if (senderCount > maxSenderCount) {
			nodeList = selectMinSendCount(nodeList);
		}
		
		return nodeList;
	}

	private List<INetNode> selectMinSendCount(List<INetNode> nodeList) {
		List<INetNode> resultList = new ArrayList<>();
		
		INetNode maxNode = nodeList.get(0);
		for (int index = 0; index < nodeList.size(); index++) {
			INetNode node = nodeList.get(index);
			if (maxNode.getSendTime() < node.getSendTime()) {
				maxNode = node;
			}
		}
		int[] sendCount = new int[maxNode.getSendTime()];
		for (INetNode node : nodeList) {
			++sendCount[node.getSendTime()];
		}
		int maxSenderCount = this.maxSenderCount;
		for (int index = 0; index < sendCount.length; index++) {
			if (maxSenderCount < 0) {
				sendCount[index] = 0;
			} else {
				maxSenderCount -= sendCount[index];
				if (maxSenderCount < 0) {
					sendCount[index] += maxSenderCount;
				}
			}
		}
		
		for (INetNode node : nodeList) {
			int index = node.getSendTime();
			if (sendCount[index] > 0) {
				resultList.add(node);
				sendCount[index]--;
			}
		}
		
 		return resultList;
	}
	
}
