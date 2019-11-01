package com.hx.cs_one2multi.client.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class One2MultiClient {
	private Socket socket;
	private int port;
	private String ip;
	
	public One2MultiClient() {
	}

	public One2MultiClient setPort(int port) {
		this.port = port;
		return this;
	}

	public One2MultiClient setIp(String ip) {
		this.ip = ip;
		return this;
	}
	
	public void talking() {
		try {
			socket = new Socket(ip, port);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			String message = "";
			
			Scanner scanner = new Scanner(System.in);;
			System.out.println("已连接服务器，请输入数据：");
			while (!message.equalsIgnoreCase("byebye")) {
				message = scanner.nextLine();
				dos.writeUTF(message);
				String mess = dis.readUTF();
				System.out.println(mess);
			}
			scanner.close();
			dis.close();
			dos.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
