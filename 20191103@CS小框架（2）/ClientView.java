package com.hx.cs_framework.test.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.hx.cs_framework.core.Client;
import com.hx.cs_framework.core.IClientAction;

public class ClientView implements IClientAction {
	private Client client;
	private volatile boolean goon;
	private int count;
	
	public ClientView() throws UnknownHostException, IOException {
		client = new Client()
				.setIp("172.20.10.3")
				.setPort(54188);
		client.setClientAction(this);
		count = 1;
	}
	
	public void connectToServer() {
		System.out.println("第"+ count++ + "次连接服务器.....");
		client.connectionToServer();		
	}
	
	private void talk() {
		Scanner in = new Scanner(System.in);
		String command = "";
		goon = true;
		
		while (goon && !command.equalsIgnoreCase("byebye")) {
			command = in.nextLine();
			client.send(command);
		}
		
		in.close();
	}

	@Override
	public void refuseOnline(String reason) {
		// 虽然改变了条件值，但是还是卡在了in.nextLine()
		goon = false;
		System.out.println("服务器拒绝上线!\n原因：" + reason);
	}

	@Override
	public void successOnline() {
		talk();
	}

	@Override
	public void afterConnectError() {
		System.out.println("连接服务器失败，是否继续连接(1继续连接，0放弃连接)？");
		Scanner in = new Scanner(System.in);
		int choose = in.nextInt();
		if (choose == 1) {
			in.close();
			connectToServer();
		} else {
			in.close();
		}
	}
	
}
