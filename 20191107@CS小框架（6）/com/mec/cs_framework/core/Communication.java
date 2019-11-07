package com.mec.cs_framework.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 这个类不单单可以在服务器端使用，也可以在客户机端使用；<br>
 * 这个类要实现的功能：
 * <ol>
 * 		<li>侦听对端发送的消息；</li>
 * 		<li>提供向对端发送消息的功能(方法)；</li>
 * </ol>
 * @author MEC-Teacher
 *
 */
public abstract class Communication implements Runnable {
	private static int no;
	protected Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private volatile boolean goon;
	
	Communication(Socket socket) throws IOException {
		this.socket = socket;
		this.dis = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
		this.goon = true;
		new Thread(this, "侦听对端消息线程-" + ++no).start();
	}
	
	abstract void dealMessage(NetMessage message);
	abstract void peerAbnormalDrop();
	
	void send(NetMessage message) {
		try {
			dos.writeUTF(message.toString());
		} catch (IOException e) {
			close();
		}
	}
	
	@Override
	public void run() {
		String message = null;
		while (goon) {
			try {
				message = dis.readUTF();
				// 应该交给“上一层”进一步处理message
				dealMessage(new NetMessage(message));
			} catch (IOException e) {
				if (goon == true) {
					goon = false;
					// 这是对端异常掉线，需要交给“上一层”进一步处理。
					peerAbnormalDrop();
				}
			}
		}
		
		close();
	}

	void close() {
		goon = false;
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
