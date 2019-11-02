package com.hx.cs_framework.core;

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
 * @author chaojidalao
 *
 */
public abstract class Communication implements Runnable {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private volatile boolean goon;
	private static int no;
	
	// 创建通信信道，因为服务器和客户端都需要建立，因此可以是接口
	public Communication(Socket socket) throws IOException {
		this.socket = socket;
		this.dis = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
		this.goon = true;
		new Thread(this, "侦听对端消息线程-" + ++no).start();
	}
	
	public abstract void dealMessage(String message);
	// 处理对端异常掉线的方法，交给上一层完成
	public abstract void peerAbnormalDrop();
	
	public void send(String message) {
		try {
			dos.writeUTF(message);
		} catch (IOException e) {
			// 如果信道出现异常（异常掉线），就应该关闭
			close();
		}
	}
	
	public void close() {
		// 就算这里赋值为false，但是对于侦听消息的线程不起作用
		// 该线程一直卡在readUTF()函数处，所以应该直接关闭信道
		goon = false;
		try {
			// 防止反复关闭
			if (dis != null) {
				dis.close();				
			}
		} catch (IOException e) {
		} finally {
			dis = null;
		}
		try {
			// 防止反复关闭
			if (dos != null) {
				dos.close();				
			}
		} catch (IOException e) {
		} finally {
			dos = null;
		}
		try {
			// 防止反复关闭
			// 关闭客户端
			if (socket != null && !socket.isClosed()) {
				socket.close();				
			}
		} catch (IOException e) {
		} finally {
			socket = null;
		}
	}

	// 接收消息是一直都需要监听的，因此需要独立的线程
	@Override
	public void run() {
		String message = null;
		while (goon) {
			try {
				message = dis.readUTF();
				// 应该进一步对接收的消息进行处理，这个处理方法应该由用户来决定
				dealMessage(message);
			} catch (IOException e) {
				// 如果对端掉线，这里会立即发现异常，需要交给“上一层”处理
				peerAbnormalDrop();
			}
		}
		// 无论是异常掉线还是正常的结束通信，都应该关闭信道和客户端
		close();
	}
}
