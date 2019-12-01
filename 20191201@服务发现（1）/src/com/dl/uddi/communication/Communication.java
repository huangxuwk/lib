package com.dl.uddi.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class Communication {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public Communication(Socket socket) throws IOException {
		this.socket = socket;
		this.dis = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
	}
	
	public void send(NetMessage netMessage) {
		try {
			dos.writeUTF(netMessage.toString());
		} catch (IOException e) {
			close();
		}
	}
	
	public abstract void dealNetMessage(NetMessage netMessage);
	
	public void checkReceive() {
		try {
			if (dis.available() > 0) {
				String message = dis.readUTF();
				dealNetMessage(new NetMessage(message));
			}
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
		} finally {
			dis = null;
		}
		try {
			if (dos != null) {
				dos.close();
			}
		} catch (IOException e) {
		} finally {
			dos = null;
		}
		try {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (IOException e) {
		} finally {
			socket = null;
		}
	}
	
}
