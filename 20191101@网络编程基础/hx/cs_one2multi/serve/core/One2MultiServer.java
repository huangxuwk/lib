package com.hx.cs_one2multi.serve.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class One2MultiServer implements Runnable {
	private ServerSocket serverSocket;
	private volatile boolean goon;
	private ServerConversation sc;
	private int port;
	
	public One2MultiServer() {
	}

	public One2MultiServer setPort(int port) {
		this.port = port;
		return this;
	}

	public void startup() {
		try {
			System.out.println("��ʼ����������......");
			serverSocket = new ServerSocket(port);
			System.out.println("�����������ɹ���");
			goon = true;
			new Thread(this, "�ͻ��������߳�").start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ֱ�ӹرշ������Ĳ�����ʵ����ƭ�Լ�
	// ͨ���ŵ���û�йص�
	public void shutdown() {
		try {
			serverSocket.close();
			// ���ûỰ��ķ����������ر�ͨ���ŵ�
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// �ͻ��������̣߳���Ϊ����������һ���׶ˣ�û���������ͻ�һֱ������
	@Override
	public void run() {
		System.out.println("��ʼ�������Կͻ��˵���������......");
		while (goon) {
			try {
				// �����߳�����
				Socket client = serverSocket.accept();
				String clientIp = client.getInetAddress().getHostAddress();
				System.out.println("�������ͻ���[" + clientIp + "]����������");
				// �����Ự�㣬��ͻ��˽�������
				sc = new ServerConversation(client);
			} catch (IOException e) {
				goon = false;
			}
		}
	}
	
}
