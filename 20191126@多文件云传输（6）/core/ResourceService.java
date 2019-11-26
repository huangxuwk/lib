package com.dl.mmfct.core;

import java.util.List;

import com.dl.mmfct.node.INetNode;
import com.dl.mmfct.resource.ResourceBaseInfo;
import com.dl.mmfct.resource.ResourceNodeRelation;
import com.dl.mmfct.resource.SendSectionInfo;
import com.dl.mmfct.send.IResourceSender;
import com.dl.mmfct.startegy.INodeSelectStrategy;
import com.dl.mmfct.startegy.IResourceAllocationStrategy;
import com.dl.mmfct.startegy.NodeSelectStartegy;
import com.dl.mmfct.startegy.ResourceAllocationStrategy;
import com.dl.rmi.core.RMIClient;
import com.dl.rmi.core.RMIClientProxy;
import com.dl.rmi.core.RMIIterfaceAnno;
import com.parser_reflect.util.PropertiesParser;
import com.timer.util.Timer;
import com.timer.util.UserAction;

@RMIIterfaceAnno(interfaces=IrmiResourceService.class)
public class ResourceService implements IrmiResourceService {
	private static volatile IResourceAllocationStrategy resourceStrategy;
	private static volatile INodeSelectStrategy nodeStrategy;
	
	private volatile boolean watting;
	
	public ResourceService() {
		if (resourceStrategy == null) {
			synchronized (ResourceService.class) {
				if (resourceStrategy == null) {
					resourceStrategy = new ResourceAllocationStrategy();
					nodeStrategy = new NodeSelectStartegy();
				}
			}
		}
	}

	private void loadResourceAllocationStrategy() {
		String rasString = PropertiesParser.findElement("ResourceAllocationStrategy");
		if (rasString != null) {
			try {
				Class<?> klass = Class.forName(rasString);
				resourceStrategy = (IResourceAllocationStrategy) klass.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String strMaxSectionLength = PropertiesParser.findElement("maxSectionLength");
			if (strMaxSectionLength != null) {
				int maxSectionLength = 0;
				try {
					maxSectionLength = Integer.valueOf(maxSectionLength);
				} catch (Exception e) {
				} finally {
					resourceStrategy.setMaxSectionLength(maxSectionLength);
				}
			}
		}
	}
	
	private void loadNodeSelectStrategy() {
		String nssString = PropertiesParser.findElement("NodeSelectStrategy");
		try {
			Class<?> klass = Class.forName(nssString);
			nodeStrategy = (INodeSelectStrategy) klass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String strMaxSenderCount = PropertiesParser.findElement("maxSenderCount");
		if (strMaxSenderCount != null) {
			int maxSenderCount = 0;
			try {
				maxSenderCount = Integer.valueOf(strMaxSenderCount);
			} catch (Exception e) {
			} finally {
				nodeStrategy.setMaxSenderCount(maxSenderCount);
			}
		}
		String strServerDoSend = PropertiesParser.findElement("serverDoSend");
		if (strServerDoSend != null) {
			boolean serverDoSend = true;
			try {
				serverDoSend = Boolean.valueOf(strServerDoSend);
			} catch (Exception e) {
			} finally {
				nodeStrategy.setServerDoSend(serverDoSend);
			}
		}
	}
	
	public void loadResourceConfig(String resConfigPath) {
		PropertiesParser.load(resConfigPath);
		loadResourceAllocationStrategy();
		loadNodeSelectStrategy();
	}
	
	@Override
	public void increaseSendTime(INetNode node) {
		ResourceNodeRelation.increaseSendTime(node);
	}

	@Override
	public int requestResource(INetNode receiver, ResourceBaseInfo rbi) {
		List<INetNode> senderList = nodeStrategy.selectNodeList(rbi);
		int sendCount = senderList.size();
		
		ResourceAllocation resourceAllocation 
			= new ResourceAllocation(receiver, senderList, rbi);
		watting = true;
		ThreadPoolFactory.execute(resourceAllocation);
		
		return sendCount;
	}

	@Override
	public void startReceive() {
		watting = false;
	}

	private class ResourceAllocation implements Runnable {
		private INetNode receiver;
		private List<INetNode> senderList;
		private ResourceBaseInfo rbi;
		private boolean ok = true;
		
		private ResourceAllocation(INetNode receiver, List<INetNode> senderList, ResourceBaseInfo rbi) {
			this.receiver = receiver;
			this.senderList = senderList;
			this.rbi = rbi;
		}

		@Override
		public void run() {
			// 防止客户端未调用startReceive()方法导致线程死循环
			Timer timer = new Timer(20000);
			UserAction action = new UserAction() {
				@Override
				public void userAction() {
					timer.stopThread();
					ok = false;
				}
			};
			timer.setUserAction(action);
			try {
				timer.startThread();
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
			while (ok && watting) {
			}
			if (!ok) {
				// 线程意外停止
				return;
			}
			List<SendSectionInfo> sendSectionList = resourceStrategy
					.allocationResource(receiver, rbi.getSiList(), senderList);
			ResourceBaseInfo tmpRbi = new ResourceBaseInfo(rbi);
			RMIClient rmiClient = new RMIClient();
			RMIClientProxy clientProxy = new RMIClientProxy(rmiClient);
			IResourceSender SenderProxy = clientProxy.getProxy(IResourceSender.class);
			for (SendSectionInfo sendSection : sendSectionList) {
				INetNode sender = sendSection.getSendNode();
				rmiClient.setRmiIp(sender.getIp());
				rmiClient.setRmiPort(sender.getPort());
				tmpRbi.setSiList(sendSection.getSectionList());
				SenderProxy.sendSectionInfo(receiver, tmpRbi);
			}
		}
		
	}
	
}
