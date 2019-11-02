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
public class ServerView implements INetListener{
	
	public ServerView() {
	}
	
	public void dosome() {
		Server server = new Server();
		// ������������۲��߶���
		// server����INetSpeaker���͵�
		// ServerView����INetListener���͵�
		// ���Խ��������ӵ�server��list��
		server.addListener(this);
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

	//����ʵ����ʾ������Ϣ�ķ�ʽ�͵ط�
	@Override
	public void parseMessage(String message) {
		System.out.println(message);
	}
}
