package com.dl.multi_file.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dl.multi_file.netWork.NetNode;
import com.dl.multi_file.progress_bar.IProgressManager;
import com.dl.multi_file.progress_bar.ProgressManager;
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
	
	/**
	 * �ṩ�������Ľӿڣ����û�����
	 */
	private volatile IProgressManager progressManager;
	/**
	 * ���ƽ������Ŀ��أ��û����Բ����ý���������ʹ��ϵͳĬ�ϵģ�
	 */
	private volatile boolean openProgressBar;
	
	public ResourceRequester() {
		localResources = new LocalResources();
	}
	
	public ResourceRequester(RequestClient client) {
		this();
		this.client = client;
	}

	public void setClient(RequestClient client) {
		this.client = client;
	}
	
	public void setNodeStrategy(INodeStrategy nodeStrategy) {
		this.nodeStrategy = nodeStrategy;
	}

	public void setResourceStrategy(IResourceStrategy resourceStrategy) {
		this.resourceStrategy = resourceStrategy;
	}
	
	/**
	 * �ṩ���û����ý������ӿڵķ�����
	 * @param progressManager �ӿ�ʵ�������
	 */
	public void setProgressManager(IProgressManager progressManager) {
		this.progressManager = progressManager;
	}
	
	/**
	 * �ṩ���û������������ķ�����Ĭ�ϲ�������
	 * @param open
	 */
	public void openProgressBar(boolean open) {
		this.openProgressBar = open;
	}

	/**
	 * �ṩ����ĳ��Դ�ķ���<br>
	 * 1���ͻ��˿��ܶ��������Դ����˿����̴߳���<br>
	 * 2�����̳߳ش�����Դ�����̣߳�
	 * @param nodeList
	 * @param resourceHandle
	 */
	public void requestResource(String resourceHandle) {
		List<String> resourceHandles = new ArrayList<>();
		resourceHandles.add(resourceHandle);
		requestResource(resourceHandles);
	}
	
	public void requestResource(List<String> resourceHandles) {
		new ThreadPoolFactory().execute(new InnerRequester(resourceHandles));
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

		public InnerRequester(List<String> resourceHandles) {
			this.resourceHandles = resourceHandles;
			resourceRecipient = new ResourceRecipient();
		}
		
		@Override
		public void run() {
			doDefaultStrategy();
			findCommonItem(resourceHandles);
			List<SectionInfo> sectionList = new ArrayList<>();
			for (String handle : resourceHandles) {
				sectionList.add(new SectionInfo(handle));
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
			// ���û�Ҫ����������
			if (openProgressBar) {
				openProgressManager(sectionList);
				resourceRecipient.setProgressManager(progressManager);
			}
			resourceRecipient.setSendCount(sendCount);
			resourceRecipient.startupReceive();
		}
		
		/**
		 * 1�����û�δ���ýӿ�ʵ���࣬��ʹ��ϵͳĬ�ϵĽ��������ߣ�<br>
		 * 2�������������ԴƬ����Ϣ��ʼ����������
		 * @param sectionList
		 */
		private void openProgressManager(List<SectionInfo> sectionList) {
			if (progressManager == null) {
				synchronized (ResourceRequester.class) {
					if (progressManager == null) {
						progressManager = new ProgressManager();
					}
				}
			}
			progressManager.initProgressBar(sectionList);
		}

		private void doDefaultStrategy() {
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
		
		/**
		 * ��ͬ��������ӵ�е���Դ��һ���������Դ�������Ĳ�ͬ��Դ�����Ӧ�Ľ���б�Ҳ��һ����
		 * ��Ҫ�ҵ�����ӵ��ȫ��������Դ�Ľ�㣻
		 * @param resourceHandles ������Դ�б�
		 * @return
		 */
		public List<NetNode> findCommonItem(List<String> resourceHandles) {
			int length = resourceHandles.size();
			List<List<NetNode>> nodeLList = new ArrayList<>();
			for (int i = 0; i < length; i++) {
				String handle = localResources.getIntactHandle(resourceHandles.get(i));
				nodeList = client.updataNews(handle);
				nodeLList.add(nodeList);
			}
			List<NetNode> nodeList = nodeLList.get(0);
			Iterator<NetNode> iterator = null;
			NetNode netNode = null;
			for (int i = 1; i < length; i++) {
				List<NetNode> tmpList = nodeLList.get(i);
				iterator = nodeList.iterator();
				while (iterator.hasNext()) {
					netNode = iterator.next();
					if (!tmpList.contains(netNode)) {
						iterator.remove();
					}
				}
			}
			this.nodeList = nodeList;
			return nodeList;
		}
		
	}
	
}
