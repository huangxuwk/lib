package com.mec.uddi.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class Communication {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private boolean shutdownNormally;
	private ThreadPoolExecutor threadPool;
	
	public Communication(Socket socket) throws IOException {
		this.shutdownNormally = false;
		this.socket = socket;
		this.dis = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
	}
	
	public Communication setThreadPool(ThreadPoolExecutor threadPool) {
		this.threadPool = threadPool;
		return this;
	}

	public void send(NetMessage netMessage) {
		try {
			dos.writeUTF(netMessage.toString());
		} catch (IOException e) {
			close();
		}
	}
	
	public abstract void dealNetMessage(NetMessage netMessage);
	public abstract void peerAbnormalDrop();
	
	public void checkReceive() {
		try {
			if (dis.available() > 0) {
				MessageProccesser mp = new MessageProccesser(dis);
				threadPool.execute(mp);
			}
		} catch (IOException e) {
			if (!shutdownNormally) {
				peerAbnormalDrop();
			}
		}
	}
	
	public void close() {
		shutdownNormally = true;
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
	
	class MessageProccesser implements Runnable {
		private DataInputStream dis;
		
		public MessageProccesser(DataInputStream dis) {
			this.dis = dis;
		}
		
		@Override
		public void run() {
			try {
				String message = dis.readUTF();
				dealNetMessage(new NetMessage(message));
			} catch (IOException e) {
			}
		}
		
	}
	
}
