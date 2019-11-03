package com.hx.cs_framework.test.server;

import java.util.Scanner;

import com.hx.cs_framework.core.INetListener;
import com.hx.cs_framework.core.Server;

/**
 * ����������㣬��һ����ǻ����棬����Ҫ����
 * INetListener�ӿ��еķ����� ��Ϊ��������о��Ѿ���ȷ��
 * Ҫ��������Ϣ�������ģ���ʲô��ʽ��������ʱֻ�������������
 * @author chaojidalao
 *
 */
public class ServerView {
	private Server server;
	
	public ServerView() {
	}
	
	public void dosome() {
		// �������������������÷������������ö˿ں�
		server = new Server();
		server.setPort(54188);
		// ������������۲��߶���
		// server����INetSpeaker���͵�
		// ServerView����INetListener���͵�
		// ���Խ��������ӵ�server��list��
		server.addListener(new INetListener() {
			@Override
			public void parseMessage(String message) {
				System.out.println(message);
			}
		});
		Scanner in = new Scanner(System.in);
		boolean finished = false;
		
		String mess = "";
		while (!finished) {
			mess = in.nextLine();
			if (mess.equalsIgnoreCase("startup")) {
				server.startup();
			} else if (mess.equalsIgnoreCase("shutdown")) {
				server.shutdown();
			} else if (mess.equalsIgnoreCase("exit")) {
				finished = false;
			}
		}
		in.close();
	}

}
