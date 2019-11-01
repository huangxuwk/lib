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
		// 建立通信信道，对端的输入和输出相连
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
	
		// readUTF()函数和scanf()函数一样都会一直等待，因此在后面要另给线程
		String mess = dis.readUTF();
		System.out.println("来自客户端的消息[" + mess + "]");
		dos.writeUTF("Server:[" + mess + "]");
	}
	
	public void startup() {
		try {
			System.out.println("开始建立服务器......");
			//建立服务器必须声明端口号
			server = new ServerSocket(54188);
			System.out.println("服务器成功建立！");
			System.out.println("开始侦听客户端连接请求......");
			//服务器侦听，accept()函数和scanf()函数一样，只要没有
			//就一直等待，返回的是一个socket类型的对象；
			Socket client = server.accept();
			String clientIp = client.getInetAddress().getHostAddress();
			System.out.println("已接收客户端" + clientIp + "连接服务器的消息！");
			listenningClient(client);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
