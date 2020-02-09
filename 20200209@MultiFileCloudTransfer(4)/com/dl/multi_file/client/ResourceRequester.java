package com.dl.multi_file.client;

import java.util.ArrayList;
import java.util.List;

import com.dl.multi_file.netWork.NetNode;
import com.dl.multi_file.resource.LocalResources;
import com.dl.multi_file.resource.SectionInfo;
import com.dl.multi_file.strategy.INodeStrategy;
import com.dl.multi_file.strategy.IResourceStrategy;
import com.dl.multi_file.strategy.NodeStrategy;
import com.dl.multi_file.strategy.ResourceStrategy;
import com.util.ThreadPoolFactory;

/**
 * 资源请求者<br>
 * 1、此类对需要申请的资源进行结点选择策略及资源分配策略；<br>
 * 2、结点选择策略和资源分配策略是可设置的，且系统带有默认的；<br>
 * 3、策略完成后进行服务器的rpc请求及资源的接收；<br>
 * 4、策略的实现和资源的接收需要放到单独的线程中完成；
 * @author dl
 *
 */
public class ResourceRequester {
	private volatile INodeStrategy nodeStrategy;
	private volatile IResourceStrategy resourceStrategy;
	
	private RequestClient client;
	private LocalResources localResources;
	
	public ResourceRequester() {
	}
	
	public ResourceRequester(RequestClient client, LocalResources localResources) {
		this();
		this.client = client;
		this.localResources = localResources;
	}

	public void setClient(RequestClient client) {
		this.client = client;
	}
	
	public void setLocalResources(LocalResources localResources) {
		this.localResources = localResources;
	}
	
	public void setNodeStrategy(INodeStrategy nodeStrategy) {
		this.nodeStrategy = nodeStrategy;
	}

	public void setResourceStrategy(IResourceStrategy resourceStrategy) {
		this.resourceStrategy = resourceStrategy;
	}

	/**
	 * 提供申请某资源的方法<br>
	 * 1、客户端可能多次请求资源，因此开启线程处理；<br>
	 * 2、用线程池处理资源请求线程；
	 * @param nodeList
	 * @param resourceHandle
	 */
	public void requestResource(List<NetNode> nodeList, String resourceHandle) {
		List<String> resourceHandles = new ArrayList<>();
		resourceHandles.add(resourceHandle);
		requestResource(nodeList, resourceHandles);
	}
	
	public void requestResource(List<NetNode> nodeList, List<String> resourceHandles) {
		new ThreadPoolFactory().execute(new InnerRequester(nodeList, resourceHandles));
	}
	
	/**
	 * 资源申请内部执行类<br>
	 * 1、将用户申请的资源句柄列表变为对应的资源信息列表；<br>
	 * 2、实现结点选择策略和资源分配策略；<br>
	 * 3、依次给选择的结点分配资源发送任务；<br>
	 * 4、开启资源接收线程；
	 * @author dl
	 *
	 */
	class InnerRequester implements Runnable {
		private ResourceRecipient resourceRecipient;
		private List<NetNode> nodeList;
		private List<String> resourceHandles;

		public InnerRequester(List<NetNode> nodeList, List<String> resourceHandles) {
			this.nodeList = nodeList;
			this.resourceHandles = resourceHandles;
			resourceRecipient = new ResourceRecipient(localResources);
		}

		@Override
		public void run() {
			doDefaultStrategy();
			List<SectionInfo> sectionList = new ArrayList<>();
			for (String handle : resourceHandles) {
				sectionList.add(localResources.getSection(handle));
			}
			List<NetNode> targetList = nodeStrategy.doNodeStrategy(nodeList);
			int sendCount = targetList.size();
			List<List<SectionInfo>> sectionLList = resourceStrategy.doResourceStrategy(sectionList, sendCount);
			
			NetNode recipientNode = resourceRecipient.getRecipientNode();
			for (int i = 0; i < sendCount; i++) {
				client.setResourceServer(targetList.get(i));
				try {
					client.requestResource(recipientNode, sectionLList.get(i));
				} catch (Throwable e) {
					// 服务器宕机，将掉线信息报告
					client.reportDropped();
					return;
				}			
			}
			// 任务安排完成后，开启接收服务器
			LeakageManager leakageManager = new LeakageManager(ResourceRequester.this);
			leakageManager.init(sectionList);
			resourceRecipient.setLeakageManager(leakageManager);
			resourceRecipient.setSendCount(sendCount);
			resourceRecipient.startupReceive();
		}
		
		public void doDefaultStrategy() {
			if (nodeStrategy == null) {
				synchronized (ResourceRequester.class) {
					if (nodeStrategy == null) {
						nodeStrategy = new NodeStrategy();
					}
				}
			}
			if (resourceStrategy == null) {
				synchronized (ResourceRequester.class) {
					if (resourceStrategy == null) {
						resourceStrategy = new ResourceStrategy();
					}
				}
			}
		}
		
	}
}
