package com.dl.mmfct.section;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface IBytesSendReceive {
	// 32k
	int DEFAULT_BUFFER_SIZE = 1 << 15;
	
	void send(DataOutputStream dos, byte[] data) throws Exception;
	byte[] receive(DataInputStream dis, int size) throws Exception;
}
