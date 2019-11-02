package com.hx.cs_framework.core;

import java.io.IOException;
import java.net.Socket;

/**
 * ����������ͨ�Ų㣬����ͻ���ͨ�Ų����ͨ��
 * ͨ�Ų���ר����ÿһ���Զ����ӵģ����ڷ�������һ����֧
 * ������Ҫ������յ���ϢҲҪ�����쳣���ߵ�����
 * ����ʵ�ֳ�����δʵ�ֵķ�����
 * @author chaojidalao
 *
 */
public class ServerConversation extends Communication {

	public ServerConversation(Socket socket) throws IOException {
		super(socket);
		
	}

	@Override
	public void dealMessage(String message) {
		// �������Կͻ��˵���Ϣ
		System.out.println("���Կͻ�������Ϣ:" + message);
	}

	@Override
	public void peerAbnormalDrop() {
		System.out.println("�ͻ����쳣���ߣ�");
	}
	
}
