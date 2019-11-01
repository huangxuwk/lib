package com.mec.cs_one2one.server.client.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class One2OneClient {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private String ip;
	private int port;
	
	public One2OneClient() {
	}

	public One2OneClient setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public One2OneClient setPort(int port) {
		this.port = port;
		return this;
	}

	public void connectToServer() {
		try {
			System.out.println("尝试连接到服务器......");
			//建立客户机需要将ip信息写入，交给服务器，并且要知道端口号
			socket = new Socket(ip, port);
			System.out.println("连接服务器成功！");
			
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			
			dos.writeUTF("hello！");
			System.out.println(dis.readUTF());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
