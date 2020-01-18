package com.dl.registry.core;

import java.io.IOException;
import java.util.List;

import com.timer.util.Timer;
import com.timer.util.UserAction;

public class Heartbeat {
	public static final int DEFAULTTIME = 60*60*1000;
	
	private Timer timer;
	private int heartBeatTime;
	private RoundRobin roundRobin;
	
	public Heartbeat() {
		roundRobin = new RoundRobin();
		heartBeatTime = DEFAULTTIME;
	}
	
	public void setTime(int heartBeatTime) {
		this.heartBeatTime = heartBeatTime;
	}
	
	public void startHeartBeat() {
		timer = new Timer(heartBeatTime);
		timer.setUserAction(new UserAction() {
			@Override
			public void userAction() {
				List<CenterConversation> conList = roundRobin.getCoList();
				NetMessage netMessage = new NetMessage();
				netMessage.setType(EMessageType.HEARTBEAT);
				for (CenterConversation centerConversation : conList) {
					try {
						centerConversation.sendMessage(netMessage);
					} catch (IOException e) {
						// ¶Ô¶ËµôÏß
						roundRobin.removeConversation(centerConversation);
					}
				}
			}
		});
		try {
			timer.startThread();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		timer.stopThread();
	}
	
}
