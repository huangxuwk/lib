package com.mec.cs_one2one.server.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class One2OneServer {
	private ServerSocket server;
	
	public One2OneServer() {
	}

	private void listenningClient(Socket socket) throws IOException {
		// ����ͨ���ŵ����Զ˵�������������
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
	
		// readUTF()������scanf()����һ������һֱ�ȴ�������ں���Ҫ����߳�
		String mess = dis.readUTF();
		System.out.println("���Կͻ��˵���Ϣ[" + mess + "]");
		dos.writeUTF("Server:[" + mess + "]");
	}
	
	public void startup() {
		try {
			System.out.println("��ʼ����������......");
			//�������������������˿ں�
			server = new ServerSocket(54188);
			System.out.println("�������ɹ�������");
			System.out.println("��ʼ�����ͻ�����������......");
			//������������accept()������scanf()����һ����ֻҪû��
			//��һֱ�ȴ������ص���һ��socket���͵Ķ���
			Socket client = server.accept();
			String clientIp = client.getInetAddress().getHostAddress();
			System.out.println("�ѽ��տͻ���" + clientIp + "���ӷ���������Ϣ��");
			listenningClient(client);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
