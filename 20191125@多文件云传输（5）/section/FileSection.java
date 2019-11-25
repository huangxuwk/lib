package com.dl.mmfct.section;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class FileSection {
	private SectionInfo section;
	private byte[] value;
	private IBytesSendReceive bytesSendReceive;
	
	public FileSection() {
		// 你先看这里的用法，我直接用类的静态方法得到了一个bytesSendReceive
		/*
		 * 懂我意思吧 
		 * 我不太懂 
		 * 因为咱们的框架只写了最基础的一部分，所以看不出来单例的好处
		 * 或者说 这里也可以不用单例
		 * 
		 * 单例模式最关键的是
		 * 这样吧 等下次遇到典型的案例 我再给你讲应用环境
		 * 我先给你讲一下怎么产生单例  好几
		 * 
		 * newInstance() 看起来是不是很神秘 哈哈   这个不是产生一个对象 
		 * 对呀 我模仿那个啥 
		 */
// 记得吧   系的
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
