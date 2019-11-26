package com.dl.mmfct.receiver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.dl.mmfct.resource.ResourceBaseInfo;
import com.dl.mmfct.view.IReceiveViewAction;

public class ReceiveServer implements Runnable {
	private ResourceBaseInfo rbi;
	
	private ServerSocket server;
	private int receivePort;
	private int senderCount;
	private volatile boolean ok;
	
	private IReceiveViewAction receiveView;
	
	public ReceiveServer() {
	}
	
	public void setRbi(ResourceBaseInfo rbi) {
		this.rbi = rbi;
	}
	
	public void setReceiveView(IReceiveViewAction receiveView) {
		this.receiveView = receiveView;
	}
	
	public void setReceivePort(int receivePort) {
		this.receivePort = receivePort;
	}
	
	public void setSenderCount(int senderCount) {
		this.senderCount = senderCount;
		// ѡ������ʾView
		if (receiveView != null) {
			receiveView.getSenderCount(senderCount);
		}
	}
	
	public void startup() {
		try {
			this.server = new ServerSocket(receivePort);
			this.ok = true;
			
			new Thread(this).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		if (this.server != null && !this.server.isClosed()) {
			try {
				this.server.close();
			} catch (IOException e) {
			} finally {
				this.server = null;
			}
		}
	}
	
	@Override
	public void run() {
		int count = 0;
		while (ok && count++ < senderCount) {
			try {
				Socket sender = server.accept();
				// ѡ���Ը�֪View�����ӵ�һ�����Ͷˡ�
				if (receiveView != null) {
					receiveView.linkedOneSender(sender);
				}
				// ����һ�������̣߳���ɾ�����չ��̣�
			//	new Receiver(sender, rbi, receiveView);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
