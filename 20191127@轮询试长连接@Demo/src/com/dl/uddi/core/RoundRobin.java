package com.dl.uddi.core;

import java.io.DataInputStream;
import java.io.IOException;

import com.dl.uddi.action.IAction;

public class RoundRobin implements Runnable {
	private IAction action;
	
	private volatile boolean goon;
	
	public RoundRobin(IAction action) {
		this.action = action;
		goon = true;
	}
	
	public void setAction(IAction action) {
		this.action = action;
	}
	
	public void setGoon(boolean goon) {
		this.goon = goon;
	}

	@Override
	public void run() {
		while (goon) {
			synchronized (ConnectionHolder.class) {
				int size = ConnectionHolder.getSize();
				for (int index = 0; index < size; index++) {
					ChannelDefinition channel = ConnectionHolder.getChannelDefinition(index);
					DataInputStream dis = channel.getDis();
					try {
						if (dis.available() > 0) {
							String value = dis.readUTF();
							ThreadPoolFactory.execute(new ActionThread(value));
						}
					} catch (IOException e) {
						ConnectionHolder.remove(channel);
					}
				}				
			}
		}
	}
	
	class ActionThread implements Runnable {
		private String value;
		
		public ActionThread(String value) {
			this.value = value;
		}

		@Override
		public void run() {
			action.dealAction(value);
		}
		
	}
	
}
