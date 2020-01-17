package com.dl.provider.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.dl.registry.core.NetMessage;
import com.util.ThreadPoolFactory;

public class VocationalWork {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
		
	public VocationalWork() {
	}
	
	public VocationalWork(Socket socket) {
		this.socket = socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public void close() {
		try {
			if (!socket.isClosed() && socket != null) {
				socket.close();		
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			socket = null;
		}
		try {
			if (dis != null) {
				dis.close();		
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dis = null;
		}
		try {
			if (dos != null) {
				dos.close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dos = null;
		}
	}
	
	private void getConnection() throws IOException {
		if (dis == null) {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		}
	}
	
	public boolean sendMessage(NetMessage message) {
		try {
			getConnection();
			dos.writeUTF(message.toString());
		} catch (IOException e) {
			// 注册中心宕机
			ThreadPoolFactory.execute(new Thread(new InnerReConnect()));
			return false;
		}
		return true;
	}
	
	// 保留方法
	public boolean readMessage() {
		try {
			getConnection();
			dis.readUTF();
		} catch (IOException e) {
			// 注册中心宕机
			ThreadPoolFactory.execute(new Thread(new InnerReConnect()));
			return false;
		}
		return true;
	}
	
	/**
	 * 开启线程处理对端的消息
	 * @author dl
	 *
	 */
	private class InnerReConnect implements Runnable {
		public InnerReConnect() {
		}

		@Override
		public void run() {
			// TODO 定时连接线程
		}
	}
}
