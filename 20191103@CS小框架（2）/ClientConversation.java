package com.hx.cs_framework.core;

import java.net.Socket;

public class ClientConversation extends Communication {
	private String id;
	private Client client;
	
	ClientConversation(Client client, Socket socket) throws Exception {
		super(socket);
		if (client.getClientAction() == null) {
			throw new Exception("IClientActionδ���壡");
		}
		this.client = client;
	}

	@Override
	void dealMessage(NetMessage message) {
		// TODO �������Է������˵���Ϣ
		// TODO ����message�е�type���ֱ������Է������˵���Ϣ
		EMessageType type = message.getType();
		String para = message.getParameter();
//		String action = message.getAction();
		switch (type) {
		case FREFUSE_ONLINE:
			// TODO 
			// �������ܾ����ӣ�����Ҫ��ͨ���̹߳ر�
			close();
			break;
		// ����˷������˸���id�����µ�id���ظ����������ע��
		case YOUR_ID:
			this.id = para;
			send(new NetMessage()
					.setType(EMessageType.REQUEST_ONLINE)
					.setParameter(id));
			break;
		case CONFIRM_ONLINE:
			// �������û�ʵ�ֵ�successOnline()����
			client.getClientAction().successOnline();
			break;
		default:
			break;
		}
		System.out.println("���Է���������Ϣ:" + message);
	}

	@Override
	void peerAbnormalDrop() {
		// TODO ����������쳣崻�
		System.out.println("�������쳣崻���");
	}

}
