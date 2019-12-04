package com.mec.uddi.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.mec.uddi.registry.CommunicationNode;
import com.mec.uddi.util.MThreadPool;

public class Communication {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private boolean shutdownNormally;
	private ICommunicationAction communicationAction;
	private MThreadPool threadPool;
	private CommunicationNode node;
	private volatile boolean isRightGuy;
	
	public Communication(Socket socket) throws IOException {
		this.isRightGuy = false;
		this.shutdownNormally = false;
		this.socket = socket;
		this.threadPool = new MThreadPool();
		this.dis = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
	}
	
	public boolean isRightGuy() {
		return isRightGuy;
	}

	public void setRightGuy(boolean isRightGuy) {
		this.isRightGuy = isRightGuy;
	}

	public void setNode(CommunicationNode node) {
		this.node = node;
	}

	public void setCommunicationAction(ICommunicationAction communicationAction) {
		this.communicationAction = communicationAction;
	}

	public void send(NetMessage netMessage) {
		try {
			dos.writeUTF(netMessage.toString());
		} catch (IOException e) {
			close();
		}
	}
	
	public void checkReceive() {
		try {
			if (dis.available() > 0) {
				MessageProccesser mp = new MessageProccesser(dis);
				threadPool.newInstance().execute(mp);
				return;
			}
			if (isRightGuy) {
				return;
			}
			long currentTime = System.currentTimeMillis();
			if (node.getLinkedTime() - currentTime >= 
					NetNodePool.getMaxDelayTime()) {
				// 删除这个节点！
				communicationAction.removeUnknowNode(node);
			}
		} catch (IOException e) {
			if (!shutdownNormally) {
				communicationAction.peerAbnormalDrop(node);
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
				communicationAction.dealNetMessage(node, new NetMessage(message));
			} catch (IOException e) {
				communicationAction.peerAbnormalDrop(node);
			}
		}
		
	}
	
}
