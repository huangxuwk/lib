package com.dl.mmfct.section;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * 大黄：因为这个类的作用其实就是发送和接收对吧，所以相当于一个中介的关系
 * 因此需要一个这个类的对象就行了，唯一不同的就是buffersize不同，这个
 * 一般来讲，在app客户端和app服务器端是两套不同得jvm，所以两端的
 * 这个类的对象可以是不同的，所以bufferSize就可以调整，自己管自己的
 * duiba?你说完了？
 * 小常：我想问的是用单例模式有啥便捷之处还有他的作用
 *  		我看了博文是不是懒汉模式和饿汉模式还有他昨天说的buffersize是啥
 * 大黄：给你看看他没用单例模式的坏处
 * 
 * @author chaojidala   哈哈哈。。。。
 *
 */
public class BytesSendReceive implements IBytesSendReceive {
	private int bufferSize;

	// 此构造方法使用默认的长度
	public BytesSendReceive() {
		this(DEFAULT_BUFFER_SIZE);
	}
	
	// 这个用户可以自己定义
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
