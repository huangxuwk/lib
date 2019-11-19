package com.dl.mmfct.section;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class FileSection {
	private SectionInfo section;
	private byte[] value;
	private IBytesSendReceive bytesSendReceive;
	
	public FileSection() {
		// ���ȿ�������÷�����ֱ������ľ�̬�����õ���һ��bytesSendReceive
		/*
		 * ������˼�� 
		 * �Ҳ�̫�� 
		 * ��Ϊ���ǵĿ��ֻд���������һ���֣����Կ������������ĺô�
		 * ����˵ ����Ҳ���Բ��õ���
		 * 
		 * ����ģʽ��ؼ�����
		 * ������ ���´��������͵İ��� ���ٸ��㽲Ӧ�û���
		 * ���ȸ��㽲һ����ô��������  �ü�
		 * 
		 * newInstance() �������ǲ��Ǻ����� ����   ������ǲ���һ������ 
		 * ��ѽ ��ģ���Ǹ�ɶ 
		 */
// �ǵð�   ϵ��
//		Class<?> klass = this.getClass();
//		Object obj = klass.newInstance();
		bytesSendReceive = SendReceiveFactory.newInstance();
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

	public void setSection(SectionInfo section) {
		this.section = section;
	}

	public SectionInfo getSection() {
		return section;
	}
	
	public void sendSection(DataOutputStream dos) throws Exception{
		bytesSendReceive.send(dos, section.toBytes());
		bytesSendReceive.send(dos, value);
	}
	
	public void getFileSection(DataInputStream dis) throws Exception {
		byte[] sectionHead = bytesSendReceive.receive(dis, SectionInfo.SECTION_INFO_LENGTH);
		section = new SectionInfo(sectionHead);
		value = bytesSendReceive.receive(dis, section.getSize());
	}
	
}
