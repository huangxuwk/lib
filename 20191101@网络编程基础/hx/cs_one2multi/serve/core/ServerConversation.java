package com.hx.cs_one2multi.serve.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * ����һ�����ڷ������ĻỰ�㣬�����������������ӵ��������Ŀͻ���
 * ����ͨ���ŵ������Խ��տͻ��˵���Ϣ�ͷ�����Ϣ
 * @author chaojidalao
 *
 */
public class ServerConversation implements Runnable {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	// ͨ���������ӷ��ص�socket��ÿͻ��˽���ͨ���ŵ���������ͨ���߳�
	public ServerConversation(Socket socket) {
		this.socket = socket;
		try {
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
		
			new Thread(this).start();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	// ������ƿͻ���ֹͣ���ӵ�����Ȩ���ڿͻ��ˣ����������Ͽ�
	// ����Ҫһ���ɷ����������ƶϵ��ͻ��˵Ĳ���
	// ����������ֱ�ӵ�������������ر�
	public void close() {
		try {
			if (dis == null) {
				return;
			}
			dis.close(); 
			dos.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dis = null;
		}
	}

	@Override
	public void run() {
		String mess = "";
		while (!mess.equalsIgnoreCase("byebye")) {
			try {
				mess = dis.readUTF();
				System.out.println("���Կͻ��˵���Ϣ[" + mess + "]");
				dos.writeUTF("����������:[" + mess + "]");
			} catch (IOException e) {
				System.out.println("������");
				break;
			}
		}
		close();
	}
}
