package com.dl.multi_file.strategy;

import java.util.ArrayList;
import java.util.List;

import com.dl.multi_file.netWork.NetNode;

/**
 * ���ѡ�����ʵ����<br>
 * 1��ͨ�����ķ��ʹ����ͷ���״̬���ж��Ƿ�Ҫ���ͣ�<br>
 * 2��ͨ��Ͱ������ѡȡ���ʹ������ٺ�ͬʱ���͸���С����ֵ�Ľ�㣻<br>
 * 3�����ͽ��ĸ����ͽ��ͬʱ����͸����ǿ����õģ�
 * @author dl
 *
 */
public class NodeStrategy implements INodeStrategy {
	/**
	 * Ĭ�Ϸ��͵Ľ�����
	 */
	public static final int DEFAULT_SEND_COUNT = 5;
	/**
	 * ����Ĭ�����ͬʱ���͸���
	 */
	public static final int DEFAULT_MAX_SENDING_COUNT = 3;
	
	private int sendCount;
	private int maxSendingCount;
	
	public NodeStrategy() {
	}
	
	public NodeStrategy(int sendCount, int maxSendingCount) {
		this.sendCount = sendCount;
		this.maxSendingCount = maxSendingCount;
	}
	
	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	public int getMaxSendingCount() {
		return maxSendingCount;
	}

	public void setMaxSendingCount(int maxSendingCount) {
		this.maxSendingCount = maxSendingCount;
	}

	@Override
	public List<NetNode> doNodeStrategy(List<NetNode> nodeList) {
		if (nodeList == null) {
			return null;
		}
		int size = nodeList.size();
		if (sendCount > size) {
			sendCount = size;
		}
		if (sendCount <= 0 ) {
			sendCount = DEFAULT_SEND_COUNT;
		}
		if (maxSendingCount <= 0) {
			maxSendingCount = DEFAULT_MAX_SENDING_COUNT;
		}
		
		/*
		 * ����Ͱ�������ҵ��ʺϵĽ��
		 */
		int maxNum = nodeList.get(0).getSendCount();
		for (int i = 1; i < size; i++) {
			int tempCount = nodeList.get(i).getSendCount();
			if (maxNum < tempCount) {
				maxNum = tempCount;
			}
		}
		int[] nodeCount = new int[++maxNum];
		for (NetNode node : nodeList) {
			nodeCount[node.getSendCount()]++;
		}
		List<NetNode> resultList = new ArrayList<>();
		for (int i = 0; i < maxNum; i++) {
			if (nodeCount[i] > 0) {
				for (NetNode netNode : nodeList) {
					if (netNode.getSendCount() == i
							&& netNode.getSendingCount() <= maxSendingCount) {
						resultList.add(netNode);
						if (resultList.size() == sendCount) {
							return resultList;
						}
					}
				}
				nodeCount[i] = 0;
			}
		}
			
		return null;
	}

}
