package com.dl.multi_file.netWork;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.dl.multi_file.resource.SectionInfo;

/**
 * �����<br>
 * 1���ṩ����Ƭ�εĽ����뷢�ͣ�<br>
 * 2���Էֶ���ʽ���ͣ�Ĭ��Ϊ8kb/�Σ�<br>
 * 3�����ݷ���ǰ���ȷ�����������
 * @author dl
 *
 */
public class Transmission {
	public static final int DEFAULT_LENGTH = 2 << 13;
	
	private int sendLength;
	
	public Transmission() {
		sendLength = DEFAULT_LENGTH;
	}
	
	public void setSendLength(int sendLength) {
		this.sendLength = sendLength;
	}
	
	/**
	 * ���շ��������͵��ֽ������ݣ�<br>
	 * �����ݷ���ǰ���ȷ����������󣬵�����Ϊnull��ʾ�����ꣻ
	 * @param dis
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public byte[] recvfrom(DataInputStream dis) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(dis);
		SectionInfo sectionInfo = (SectionInfo) ois.readObject();
		if (sectionInfo == null) {
			return null;
		}
		int size = sectionInfo.getSize();
		byte[] data = new byte[size];
		
		int restLen = size;
		int readLen;
		int len;
		int offset = 0;
		
		while (restLen > 0) {
			len = restLen > sendLength ? sendLength : restLen;
			readLen = dis.read(data, offset, len);
			restLen -= readLen;
			offset += readLen;
		}
		
		return data;
	}
	
	/**
	 * ������������
	 * @param dos
	 * @param sectionInfo
	 * @throws IOException
	 */
	public void sendObject(DataOutputStream dos, SectionInfo sectionInfo) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(dos);
		oos.writeObject(sectionInfo);
	}
	
	/**
	 * �����ֽ�����
	 * @param dos
	 * @param data
	 * @throws IOException
	 */
	public void sendData(DataOutputStream dos, byte[] data) throws IOException {
		dos.write(data);
	}
}
