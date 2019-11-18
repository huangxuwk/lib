package com.dl.mmfct.section;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * ��ƣ���Ϊ������������ʵ���Ƿ��ͺͽ��ն԰ɣ������൱��һ���н�Ĺ�ϵ
 * �����Ҫһ�������Ķ�������ˣ�Ψһ��ͬ�ľ���buffersize��ͬ�����
 * һ����������app�ͻ��˺�app�������������ײ�ͬ��jvm���������˵�
 * �����Ķ�������ǲ�ͬ�ģ�����bufferSize�Ϳ��Ե������Լ����Լ���
 * duiba?��˵���ˣ�
 * С���������ʵ����õ���ģʽ��ɶ���֮��������������
 *  		�ҿ��˲����ǲ�������ģʽ�Ͷ���ģʽ����������˵��buffersize��ɶ
 * ��ƣ����㿴����û�õ���ģʽ�Ļ���
 * 
 * @author chaojidala   ��������������
 *
 */
public class BytesSendReceive implements IBytesSendReceive {
	private int bufferSize;

	// �˹��췽��ʹ��Ĭ�ϵĳ���
	public BytesSendReceive() {
		this(DEFAULT_BUFFER_SIZE);
	}
	
	// ����û������Լ�����
	public BytesSendReceive(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	@Override
	public void send(DataOutputStream dos, byte[] data) throws Exception {
		dos.write(data);
	}

	@Override
	public byte[] receive(DataInputStream dis, int size) throws Exception {
		byte[] data = new byte[size];
		
		int restLen = size;
		int readLen;
		int len;
		int offset = 0;
		
		while (restLen > 0) {
			len = restLen > bufferSize ? bufferSize : restLen;
			readLen = dis.read(data, offset, len);
			restLen -= readLen;
			offset += readLen;
		}
		
		return data;
	}

}
