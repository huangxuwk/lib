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
	private Server server;
	private String id;
	
	// ÿ���ͻ��˵�idӦ���Ƕ��еģ���ô�Ϳ��Ը���ip�͵�ǰʱ��ȷ��
	// ���пͻ������ӳɹ�ʱ�ͷ���һ��id���ͻ��ˣ�����ע��qq�ŵķ�ʽ
	ServerConversation(Server server ,Socket socket) throws IOException {
		super(socket);
		this.server  = server;
	}
	
	void sendId() {
		String ip = socket.getInetAddress().getHostAddress();
		String id = ip + "@" + System.currentTimeMillis();
		send(new NetMessage()
				.setType(EMessageType.YOUR_ID)
				.setParameter(id));
	}

	@Override
	void dealMessage(NetMessage message) {
		// TODO �������Կͻ��˵���Ϣ
		// TODO ����message�е�type��action�����ִ������Կͻ��˵���Ϣ
		EMessageType type = message.getType();
		String para = message.getParameter();
		String action = message.getAction();
		switch (type) {
		// ������������
		case REQUEST_ONLINE:
			if (!server.addServerConvasation(para, this)) {
				close();
			}
			this.id = para;
			server.reportMessage("�ͻ���["+ para + "]����");
			send(new NetMessage()
					.setType(EMessageType.CONFIRM_ONLINE));
			break;
		default :
			break;
		}
		System.out.println("���Կͻ�������Ϣ:" + message);
	}

	@Override
	void peerAbnormalDrop() {
		System.out.println("�ͻ����쳣���ߣ�");
	}
	
}
