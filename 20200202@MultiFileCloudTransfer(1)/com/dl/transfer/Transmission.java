package com.dl.transfer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.dl.file.SectionInfo;

/**
 * 传输层<br>
 * 1、提供数据片段的接收与发送；<br>
 * 2、以分段形式发送，默认为8kb/次；<br>
 * 3、数据发送前，先发送描述对象；
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
	
	public byte[] recvfrom(DataInputStream dis) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(dis);
		SectionInfo sectionInfo = (SectionInfo) ois.readObject();
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
	
	public void sendObject(DataOutputStream dos, SectionInfo sectionInfo) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(dos);
		oos.writeObject(sectionInfo);
	}
	
	public void sendData(DataOutputStream dos, byte[] data) throws IOException {
		dos.write(data);
	}
}
