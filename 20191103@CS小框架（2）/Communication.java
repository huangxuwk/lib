package com.hx.cs_framework.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * ����಻���������ڷ�������ʹ�ã�Ҳ�����ڿͻ�����ʹ�ã�<br>
 * �����Ҫʵ�ֵĹ��ܣ�
 * <ol>
 * 		<li>�����Զ˷��͵���Ϣ��</li>
 * 		<li>�ṩ��Զ˷�����Ϣ�Ĺ���(����)��</li>
 * </ol>
 * @author chaojidalao
 *
 */
public abstract class Communication implements Runnable {
	protected Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private volatile boolean goon;
	private static int no;
	
	// ����ͨ���ŵ�����Ϊ�������Ϳͻ��˶���Ҫ��������˿����ǽӿ�
	Communication(Socket socket) throws IOException {
		this.socket = socket;
		this.dis = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
		this.goon = true;
		new Thread(this, "�����Զ���Ϣ�߳�-" + ++no).start();
	}
	
	abstract void dealMessage(NetMessage message);
	// ����Զ��쳣���ߵķ�����������һ�����
	abstract void peerAbnormalDrop();
	
	void send(NetMessage message) {
		try {
			dos.writeUTF(message.toString());
		} catch (IOException e) {
			// ����ŵ������쳣���쳣���ߣ�����Ӧ�ùر�
			close();
		}
	}
	
	void close() {
		// �������︳ֵΪfalse�����Ƕ���������Ϣ���̲߳�������
		// ���߳�һֱ����readUTF()������������Ӧ��ֱ�ӹر��ŵ�
		goon = false;
		try {
			// ��ֹ�����ر�
			if (dis != null) {
				dis.close();				
			}
		} catch (IOException e) {
		} finally {
			dis = null;
		}
		try {
			// ��ֹ�����ر�
			if (dos != null) {
				dos.close();				
			}
		} catch (IOException e) {
		} finally {
			dos = null;
		}
		try {
			// ��ֹ�����ر�
			// �رտͻ���
			if (socket != null && !socket.isClosed()) {
				socket.close();				
			}
		} catch (IOException e) {
		} finally {
			socket = null;
		}
	}

	// ������Ϣ��һֱ����Ҫ�����ģ������Ҫ�������߳�
	@Override
	public void run() {
		String message = null;
		while (goon) {
			try {
				message = dis.readUTF();
				// Ӧ�ý�һ���Խ��յ���Ϣ���д������������Ӧ�����û�������
				dealMessage(new NetMessage(message));
			} catch (IOException e) {
				if (goon == true) {
					goon = false;	
				}
				// ����Զ˵��ߣ���������������쳣����Ҫ��������һ�㡱����
				peerAbnormalDrop();
			}
		}
		// �������쳣���߻��������Ľ���ͨ�ţ���Ӧ�ùر��ŵ��Ϳͻ���
		close();
	}
}
