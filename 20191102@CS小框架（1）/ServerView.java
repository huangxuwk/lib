package com.hx.cs_framework.test.server;

import java.util.Scanner;

import com.hx.cs_framework.core.INetListener;
import com.hx.cs_framework.core.Server;

/**
 * 服务器界面层，这一层就是画界面，并且要处理
 * INetListener接口中的方法， 因为在这个类中就已经明确了
 * 要将反馈信息反馈到哪，以什么方式，这里暂时只是输出在命令行
 * @author chaojidalao
 *
 */
public class ServerView implements INetListener{
	
	public ServerView() {
	}
	
	public void dosome() {
		Server server = new Server();
		// 将这个界面加入观察者队列
		// server就是INetSpeaker类型的
		// ServerView就是INetListener类型的
		// 所以将本类对象加到server的list中
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

	//具体实现显示反馈信息的方式和地方
	@Override
	public void parseMessage(String message) {
		System.out.println(message);
	}
}
