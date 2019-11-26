package com.dl.mmfct.receiver;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.dl.mmfct.resource.ResourceBaseInfo;
import com.dl.mmfct.view.IReceiveViewAction;

public class Receiver implements Runnable {
	private Socket socket;
	private DataInputStream dis;
	private IReceiveViewAction receiveView;
	private ResourceBaseInfo rbi;
	Receiver(Socket socket, IReceiveViewAction receiveView, ResourceBaseInfo rbi) {
		this.socket = socket;
		this.receiveView = receiveView;
		this.rbi = rbi;
		new Thread(this).start();
	}
	@Override
	public void run() {
		try {
			dis = new DataInputStream(socket.getInputStream());
			
			
		} catch (IOException e) {
		}
	}
	
	
}
