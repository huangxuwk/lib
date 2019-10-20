package com.dl.rmi.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.dl.util.ArgumentMaker;

public class RMIServerAcitoner implements Runnable {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;

	public RMIServerAcitoner(Socket socket) {
		this.socket = socket;
		
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			
			new Thread(this).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void close() {
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
		try {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			socket = null;
		}
	}
	
	@Override
	public void run() {
		try {
			String interfacesName = dis.readUTF();
			String methodName = dis.readUTF();
			String argsString = dis.readUTF();
			// 根据methodName, 不但要找到方法，还要找到类
			// 真正执行method， 并返回值
			
			Object result = new ServerDealMethod().invokeMethod(interfacesName, methodName, argsString);
			dos.writeUTF(ArgumentMaker.gson.toJson(result));
			
			// 一次通信，就要关闭通信信道
			close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

	

