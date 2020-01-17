package com.dl.registry.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 本类为通信类，提供基本的收发信息的手段，为轮询做准备；
 * @author dl
 *
 */
public abstract class Communication {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
		
	public Communication() {
	}
	
	public Communication(Socket socket, DataInputStream dis, DataOutputStream dos) {
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
	}

	public DataInputStream getDis() {
		return dis;
	}

	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}
		
	public Communication(Socket socket) {
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
	
	public abstract void dealMessage(Communication communication, String message);
	
	/**
	 * 将整合的消息发送给对端；<br>
	 * 如果通信断裂，则从缓存中移除该socket（抛异常）;
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(NetMessage message) throws IOException {
		dos.writeUTF(message.toString());
	}
	
	/**
	 * 判断是否有可读消息；如果有消息，将消息发送给线程执行；<br>
	 * 如果通信断裂，则从缓存中移除该socket（抛异常）;
	 * @return
	 * @throws IOException
	 */

	public String readMessage() throws IOException {
		return dis.readUTF();
	}
	
	public boolean isReadSuccess() throws IOException {
		return dis.available() > 0;
	}
	
}
