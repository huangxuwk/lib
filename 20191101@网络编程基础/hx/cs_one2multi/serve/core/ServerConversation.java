package com.hx.cs_one2multi.serve.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 这是一个属于服务器的会话层，它的作用是与已连接到服务器的客户端
 * 建立通信信道，用以接收客户端的信息和发送信息
 * @author chaojidalao
 *
 */
public class ServerConversation implements Runnable {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	// 通过侦听连接返回的socket与该客户端建立通信信道，并开启通话线程
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
	
	// 这个控制客户端停止连接的主动权在于客户端，属于主动断开
	// 还需要一个由服务器来控制断掉客户端的操作
	// 服务器可以直接调用这个方法来关闭
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
				System.out.println("来自客户端的消息[" + mess + "]");
				dos.writeUTF("服务器返回:[" + mess + "]");
			} catch (IOException e) {
				System.out.println("结束！");
				break;
			}
		}
		close();
	}
}
