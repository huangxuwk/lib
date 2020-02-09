package com.dl.multi_file.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.dl.multi_file.netWork.NetNode;
import com.dl.multi_file.netWork.Transmission;
import com.dl.multi_file.resource.LocalResources;
import com.dl.multi_file.resource.SectionInfo;
import com.dl.multi_file.server.ResourceServer;
import com.util.ThreadPoolFactory;

/**
 * ��Դ������<br>
 * 1���������ݵķ��������в�ͬ�����Դ�Ľ��գ�<br>
 * 2�����ڴ����жϿ�������Ҫ���й������ĵ�rpc�����Ͷϵ�������<br>
 * 3��һ���ͻ������ͬʱ����һ����Դ�����ߣ�
 * @author dl
 *
 */
public class ResourceRecipient implements Runnable {
	private static Transmission transmission = new Transmission();
	
	private ServerSocket recipient;
	private NetNode recipientNode;

	private LocalResources localResources;
	private LeakageManager leakageManager;
	
	private int sendCount;
	private int successCount;
	private Map<String, RandomAccessFile> randomMap;
	
	private List<InnerReceiver> innerReceiverList;
	
	public ResourceRecipient() {
		randomMap = new HashMap<>();
		innerReceiverList = new ArrayList<>();
	}
	
	public ResourceRecipient(LocalResources localResources) {
		this();
		this.localResources = localResources;
	}
	
	public void setLocalResources(LocalResources localResources) {
		this.localResources = localResources;
	}
	
	public void setLeakageManager(LeakageManager leakageManager) {
		this.leakageManager = leakageManager;
	}
	
	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}
	
	/**
	 * �õ��������ߵĽ����Ϣ<br>
	 * 1��ip��port��ɵ�recipientNode����<br>
	 * 2����Ϊ�գ����ȴ���recipientNode����
	 * @return
	 */
	public NetNode getRecipientNode() {
		if (this.recipientNode == null) {
			String ip = ResourceServer.getLocalIp();
			if (!PortPool.hasNext()) {
				return null;
			}
			int port = PortPool.borrow();
			NetNode node = new NetNode(ip, port);
			this.recipientNode = node;
		}
		return recipientNode;
	}
	
	/**
	 * 1���������������������߳�;<br>
	 * 2��������ʱ����������ֵʱ�佫ֹͣ������Դ�ͽ��жϵ�������<br>
	 * 3���黹�˿ںţ�
	 */
	public void startupReceive() {
		try {
			recipient = new ServerSocket(recipientNode.getPort());
			new ThreadPoolFactory().execute(this);
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				int timeCount = 0;
				@Override
				public void run() {
					if ((++timeCount) >= 10 || sendCount == successCount) {
						shutdown();
						timer.cancel();
						System.out.println("��ʱ��");
					}
					System.out.println("��ʱ");
				}
			}, 0, 1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �رս��շ��������ر��߳����̣߳��ر�д�ļ����󣻶ϵ�������
	 */
	private void shutdown() {
		try {
			if (recipient != null && recipient.isClosed()) {
				recipient.close();				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			recipient = null;
		}
		
		// �����Դ����
		for (InnerReceiver innerReceiver : innerReceiverList) {
			innerReceiver.closeDis();
		}
		
		Collection<RandomAccessFile> randomList = randomMap.values();
		for (RandomAccessFile randomAccessFile : randomList) {
			try {
				if (randomAccessFile != null) {
					randomAccessFile.close();					
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				randomAccessFile = null;
			}
		}
//		leakageManager.checkReceiveCompleted();
		PortPool.giveBack(recipientNode.getPort());
	}
	
	/**
	 * ��д���ļ���RandomAccessFile���������У�
	 * ��ֹ��δ򿪺͹رգ��Ƚ�����Ϻ�ͳһ�رգ�
	 * @param path
	 * @return
	 */
	private RandomAccessFile getRandomAccessFile(String path) {
		RandomAccessFile random = randomMap.get(path);
		if (random == null) {
			random = localResources.getRandomAccessFile(path);
			randomMap.put(path, random);
		}
		return random;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < sendCount; i++) {
			try {
				Socket receiver = recipient.accept();
				InnerReceiver inReceiver = new InnerReceiver(receiver); 
				innerReceiverList.add(inReceiver);
				new ThreadPoolFactory().execute(new Thread(inReceiver));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �ڲ���������<br>
	 * 1�����ݸ����Ľ����ߵõ�ͨ���ŵ���<br>
	 * 2���ȶ�ȡSectionInfo���󣬸����Ƿ�Ϊnull���ж��Ƿ��ȡ��ɣ�<br>
	 * 3���������Ҫ���߳̽���ʱ��successCount��Ǽ�һ��
	 * @author dl
	 *
	 */
	class InnerReceiver implements Runnable {
		private Socket receiver;
		private DataInputStream dis;
		
		public InnerReceiver(Socket receiver) {
			this.receiver = receiver;
		}
		
		@Override
		public void run() {
			System.out.println("�ȴ�������Դ");
			try {
				dis = new DataInputStream(receiver.getInputStream());
				
				SectionInfo section;
				byte[] datas;
				localResources.setRootPath("N:\\");
				while ((section = transmission.recvfrom(dis)) != null) {
					datas = transmission.recvfrom(dis, section.getSize());
					RandomAccessFile random = getRandomAccessFile(section.getFileName());
					localResources.writeInLocal(random, datas, section.getOffset(), section.getSize());
					leakageManager.receiverNews(section);
				}
				System.out.println("������Դwan");
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
			} finally {
				successCount++;
				innerReceiverList.remove(this);
			}
			System.out.println("�߳̽���");
		}
		
		public void closeDis() {
			try {
				if (dis != null) {
					dis.close();	
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				dis = null;
			}
		}
	}
	
}
