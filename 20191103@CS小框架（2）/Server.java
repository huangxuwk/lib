package com.hx.cs_framework.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ����Ӧ�ð������з������Ļ�������
 * �������򿪷����������������̣߳��رշ�����
 * ����Ĺ��ܺ����ٽ������
 * @author chaojidalao
 *
 */
public class Server implements Runnable, INetSpeaker {
	private int port;
	private ServerSocket server;
	private volatile boolean goon;
	// �洢���еĶ��лỰ�㣬��������ͻ��˵�ͨ�����ڴ�
	// ʹ��map�ĺô��������Ǽ�ֵ�Ե���ʽ������ʵ��ָ������ͨ��
	// ������id�����id�Ǿ�����Ҫ��������Ƕ��е�
	private Map<String, ServerConversation> conversationMap;
	private List<INetListener> listenerList;
	
	private static int MAX_CLIENT_COUNT = 1;
	
	public Server() {
		listenerList = new ArrayList<>();
		conversationMap = new HashMap<>();
	}
	
	public void setPort(int port) {
		this.port = port;
	}

	public static int getMAX_CLIENT_COUNT() {
		return MAX_CLIENT_COUNT;
	}

	public static void setMAX_CLIENT_COUNT(int mAX_CLIENT_COUNT) {
		MAX_CLIENT_COUNT = mAX_CLIENT_COUNT;
	}

	// �򿪷������������������߳�
	public void startup() {
		// ����������������������ٴ�����
		if (goon) {
			// ͨ���۲���ģʽ���Ϳ�����ʾ�ڷ�������������
			// ��Ȼ���ڲ���֪�����ĸ����棬ͨ�����ַ�ʽ
			reportMessage("�������������������ٴ�������");
			return;
		}
		try {
			reportMessage("��ʼ����������......");
			server = new ServerSocket(port);
			reportMessage("��������������");
			goon = true;
			// ���������߳�
			new Thread(this, "�����ͻ�����������").start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		if (!goon) {
			reportMessage("��������崻��������ٴ�崻���");
			return;
		}
		// ���пͻ����ڽ���ͨ��
		if (conversationMap.size() > 0 ) {
			reportMessage("���пͻ������ߣ�����崻���");
		}
		close();
		reportMessage("��������崻���");
	}
	
	boolean addServerConvasation(String id, ServerConversation serverConversation) {
		// TODO ��ü��һ�£����serverConversation���һ�������Ƿ�Ƶ��
		// �����ӹ���Ƶ�����򣬷���false����close����Ự
		conversationMap.put(id, serverConversation);
		return true;
	}
	
	private void close() {
		goon = false;
		if (server != null) {
			try {
				// ������һ�رգ������߳̾ͻ���������
				server.close();
			} catch (IOException e) {
			} finally {
				server = null;
			}
		}
	}
	
	// �����̣߳��������ϵ������ͻ��˵�����
	@Override
	public void run() {
		reportMessage("��ʼ�����ͻ�����������......");
		while (goon) {
			try {
				Socket socket = server.accept();
				// ����һ���ͻ��˾�Ҫ����˽�еĻỰ��
				ServerConversation conversation = new ServerConversation(this ,socket);
				// ���ͻ����������ﵽ���ޣ� ��Ӧ�þܾ�����
				if (conversationMap.size() >= MAX_CLIENT_COUNT) {
					conversation.send(new NetMessage()
							.setType(EMessageType.FREFUSE_ONLINE)
							.setParameter("���������������Ժ������ӣ�"));
					// �������Ϣ���������ӵ�״̬�·��͵ģ�������Ҫ�ر�����
					conversation.close();
				} else {
					// ���û�ܽ����ӣ��ͽ�ע����˺ŷ������ͻ���
					conversation.sendId();
				}
			} catch (IOException e) {
				// ����������رգ��ͻ��������쳣
				goon = false;
			}
			
		}
	}

	@Override
	public void addListener(INetListener listener) {
		if (listenerList.contains(listener)) {
			return;
		}
		listenerList.add(listener);
	}

	@Override
	public void removeListener(INetListener listener) {
		if (!listenerList.contains(listener)) {
			return;
		}
		listenerList.remove(listener);				
	}

	void reportMessage(String message) {
		for (INetListener listener : listenerList) {
			listener.parseMessage(message);
		}
	}

}
