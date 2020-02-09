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
 * ��Դ������<br>
 * 1���������Ҫ�������Դ���н��ѡ����Լ���Դ������ԣ�<br>
 * 2�����ѡ����Ժ���Դ��������ǿ����õģ���ϵͳ����Ĭ�ϵģ�<br>
 * 3��������ɺ���з�������rpc������Դ�Ľ��գ�<br>
 * 4�����Ե�ʵ�ֺ���Դ�Ľ�����Ҫ�ŵ��������߳�����ɣ�
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
	 * �ṩ����ĳ��Դ�ķ���<br>
	 * 1���ͻ��˿��ܶ��������Դ����˿����̴߳���<br>
	 * 2�����̳߳ش�����Դ�����̣߳�
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
	 * ��Դ�����ڲ�ִ����<br>
	 * 1�����û��������Դ����б��Ϊ��Ӧ����Դ��Ϣ�б�<br>
	 * 2��ʵ�ֽ��ѡ����Ժ���Դ������ԣ�<br>
	 * 3�����θ�ѡ��Ľ�������Դ��������<br>
	 * 4��������Դ�����̣߳�
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
					// ������崻�����������Ϣ����
					client.reportDropped();
					return;
				}			
			}
			// ��������ɺ󣬿������շ�����
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
