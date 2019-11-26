package com.dl.mmfct.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.dl.mmfct.node.INetNode;

public class ResourceNodeRelation implements IrmiResourceNodeRelation {
	private static final Map<Integer, List<Integer>> relationMap = 
			new ConcurrentHashMap<Integer, List<Integer>>();
	private static final Map<Integer, INetNode> nodePool = 
			new ConcurrentHashMap<Integer, INetNode>();
	private static final Map<Integer, ResourceBaseInfo> resourcePool =
			new ConcurrentHashMap<Integer, ResourceBaseInfo>();
	
	public ResourceNodeRelation() {
	}
	
	@Override
	public void registyNode(INetNode node, List<ResourceBaseInfo> rbiList) {
		int nodeHashcode = node.hashCode();
		nodePool.put(nodeHashcode, node);
		int rbiHashcode = 0;
		for (ResourceBaseInfo rbi : rbiList) {
			rbiHashcode = rbi.hashCode();
			resourcePool.put(rbiHashcode, rbi);
			
			List<Integer> nodeList = relationMap.get(rbiHashcode);
			if (nodeList == null) {
				nodeList = new  LinkedList<Integer>();
				relationMap.put(rbiHashcode, nodeList);
			}
			nodeList.add(nodeHashcode);
		}
	}

	@Override
	public void registyNode(INetNode node, ResourceBaseInfo rbi) {
		List<ResourceBaseInfo> rbiList = new ArrayList<ResourceBaseInfo>();
		rbiList.add(rbi);
		registyNode(node, rbiList);
	}

	@Override
	public void logoutNode(INetNode node) {
		int nodeHashcode = node.hashCode();
		nodePool.remove(nodeHashcode);
		// ��������߳�
		new Thread(new CleanNode(nodeHashcode)).start();
	}
	
/*
 * ���ͻ��˷����ļ�ǰ�����÷���������netNode��ͬʱ���͵��ļ���
 * �ļ�������ɺ󣬵���increaseSendTime()�������������ӷ��ʹ�����Ҳ�������ڷ��͵ĸ��� 
 */
	public static void increaseSendTime(INetNode node) {
		int nodeId = node.hashCode();
		INetNode targetNode = nodePool.get(nodeId);
		if (targetNode == null) {
			return;
		}

		targetNode.increaseSendTime();
	}
	// TODO �ж����ڵĿͻ����Ƿ��ڷ��ļ���ͬʱ����3�����ɷ�����
	public List<INetNode> getNodeListByResource(ResourceBaseInfo rbi) {
		List<INetNode> nodeList = new ArrayList<>();
		
		int rbiHashcode = rbi.hashCode();
		List<Integer> nodeIdList = relationMap.get(rbiHashcode);
		if (nodeIdList == null) {
			return null;
		}
		for (int index = 0; index < nodeIdList.size(); index++) {
			int nodeId = nodeIdList.get(index);
			INetNode node = nodePool.get(nodeId);
			nodeList.add(node);
		}
		
		return nodeList;
	}

	private void cleanNodeList(int nodeHashcode) {
		// �����ֶκܶ�
		Set<List<Integer>> nodeSet = (Set<List<Integer>>) relationMap.values();
		Iterator<List<Integer>> iterator = nodeSet.iterator();
		List<Integer> nodeList;
		while (iterator.hasNext()) {
			nodeList = iterator.next();
			nodeList.remove(nodeHashcode);
		}
	}
	
	class CleanNode implements Runnable {
		private int nodeHashcode;
		
		public CleanNode(int nodeHashcode) {
			this.nodeHashcode = nodeHashcode;
		}
		
		@Override
		public void run() {
			synchronized (ResourceNodeRelation.class) {
				cleanNodeList(nodeHashcode);
			}
		}
		
	}
	
}
