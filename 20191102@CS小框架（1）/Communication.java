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
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private volatile boolean goon;
	private static int no;
	
	// ����ͨ���ŵ�����Ϊ�������Ϳͻ��˶���Ҫ��������˿����ǽӿ�
	public Communication(Socket socket) throws IOException {
		this.socket = socket;
		this.dis = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
		this.goon = true;
		new Thread(this, "�����Զ���Ϣ�߳�-" + ++no).start();
	}
	
	public abstract void dealMessage(String message);
	// ����Զ��쳣���ߵķ�����������һ�����
	public abstract void peerAbnormalDrop();
	
	public void send(String message) {
		try {
			dos.writeUTF(message);
		} catch (IOException e) {
			// ����ŵ������쳣���쳣���ߣ�����Ӧ�ùر�
			close();
		}
	}
	
	public void close() {
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
				dealMessage(message);
			} catch (IOException e) {
				// ����Զ˵��ߣ���������������쳣����Ҫ��������һ�㡱����
				peerAbnormalDrop();
			}
		}
		// �������쳣���߻��������Ľ���ͨ�ţ���Ӧ�ùر��ŵ��Ϳͻ���
		close();
	}
}
