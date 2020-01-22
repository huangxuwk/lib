package com.dl.provider.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import com.dl.registry.core.Communication;
import com.dl.registry.core.EMessageType;
import com.dl.registry.core.NetMessage;
import com.util.ArgumentMaker;

/**
 * �����ṩ�ߵĻỰ��<br>
 * 1����ע�����ĻỰ��ͨ�ţ�<br>
 * 2���ṩ��ע�����ĻỰ�����ƵĹ��ܣ�<br>
 * 3��֧��ע������崻���Ĳ�����
 * 
 * @author dl
 *
 */
public class ProviderConversation extends Communication implements Runnable {
	private ITimingQuery timingQuery;
	private Provider provider;
	
	private volatile boolean goon;
	
	public ProviderConversation() {
		goon = true;
	}
	
	public ProviderConversation(Socket client, DataInputStream dis, DataOutputStream dos) {
		super(client, dis, dos);
		goon = true;
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	public void setTimingQuery(ITimingQuery timingQuery) {
		this.timingQuery = timingQuery;
	}

	private String serviceMessage(List<String> serviceList) {
		return ArgumentMaker.gson.toJson(serviceList);
	}
	
	public boolean registryService(List<String> serviceList) {
		NetMessage netMessage = new NetMessage()
				.setType(EMessageType.REGISTRY)
				.setParamater(serviceMessage(serviceList));

		try {
			sendMessage(netMessage);
		} catch (IOException e) {
			// ͨ���ж�
			timingQuery.dealTimingQuery(provider);
			return false;
		}
		
		return true;
	}
	
	public boolean cancellationService(List<String> serviceList) {
		NetMessage netMessage = new NetMessage()
				.setType(EMessageType.CANCELLATION)
				.setParamater(serviceMessage(serviceList));
		
		try {
			sendMessage(netMessage);
		} catch (IOException e) {
			// ͨ���ж�
			timingQuery.dealTimingQuery(provider);
			return false;
		}
		
		return true;
	}
	
	public void shutdown() {
		goon = false;
		close();
	}
	
	// ��������
	@Override
	public void dealMessage(String message) {
	}

	@Override
	public void run() {
		while (goon) {
			try {
				String message = readMessage();
				dealMessage(message);
			} catch (IOException e) {
				timingQuery.dealTimingQuery(provider);
			}
		}
	}

}
