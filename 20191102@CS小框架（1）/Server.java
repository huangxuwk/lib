package com.hx.cs_framework.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
	private List<ServerConversation> conversationList;
	private List<INetListener> listenerList;
	
	public Server() {
		listenerList = new ArrayList<>();
		conversationList = new ArrayList<>();
	}
	
	public Server setPort(int port) {
		this.port = port;
		return this;
	}

	public void shutdown() {
		if (!goon) {
			reportMessage("��������崻��������ٴ�崻���");
		}
		// ���пͻ����ڽ���ͨ��
		if (conversationList.size() > 0 ) {
			reportMessage("���пͻ������ߣ�����崻���");
		}
		close();
		reportMessage("��������崻���");
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
	
	// �����̣߳��������ϵ������ͻ��˵�����
	@Override
	public void run() {
		reportMessage("��ʼ�����ͻ�����������......");
		while (goon) {
			try {
				Socket socket = server.accept();
				//����һ���ͻ��˾�Ҫ����˽�еĻỰ��
				new ServerConversation(socket);
			} catch (IOException e) {
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

	@Override
	public void reportMessage(String message) {
		for (INetListener listener : listenerList) {
			listener.parseMessage(message);
		}
	}

}
