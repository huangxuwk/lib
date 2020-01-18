package com.dl.provider.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

import com.parser_reflect.util.PropertiesParser;

/**
 * �����ṩ��<br>
 * 1����ע������ע��/ע������<br>
 * 2��ע������崻�����ȡ��ʱ�����ķ�ʽ��ֱ�����ӳɹ�Ϊֹ��
 * 
 * @author chaojidalao
 *
 */
public class Provider {
	public static final String DEFAULT_IP = "127.0.0.1";
	public static final int DEFAULT_PORT = 55555;
	
	private Socket client;
	private int port;
	private String ip;
	
	private ITimingQuery timingQuery;
	private ProviderConversation conversation;
	
	public Provider() {
		readConfig("/RPCConfig.properties");
		this.ip = DEFAULT_IP;
		this.port = DEFAULT_PORT;
	}

	public int getPort() {
		return port;
	}

	public void setTimingQuery(ITimingQuery timingQuery) {
		this.timingQuery = timingQuery;
	}
	
	public void setPort(int port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public void readConfig(String path) {
		PropertiesParser.load(path);
		
		String ip = PropertiesParser.findElement("ip");
		if (ip != null && !ip.equals("")) {
			this.ip = ip;
		}
		String portStr = PropertiesParser.findElement("port");
		try {
			if (portStr != null && !portStr.equals("")) {
				int port = Integer.valueOf(portStr);
				if (port > 0 && port < 65536) {
					this.port = port;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean startup() {
		return startup(false);
	}
	
	boolean startup(boolean timing) {
		try {
			System.out.println("provider��" + ip + " ," + port);
			client = new Socket(ip, port);
			DataInputStream dis = new DataInputStream(client.getInputStream());
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			conversation = new ProviderConversation(client, dis, dos);
			conversation.setTimingQuery(timingQuery);
			conversation.setProvider(this);
			new Thread(conversation).start();
		} catch (Exception e) {
			if (!timing) {
				timingQuery.dealTimingQuery(this);				
			}
			return false;
		}
		return true;
	}
	
	public void shutdown() {
		conversation.shutdown();
	}
	
	/**
	 * ע������ɸ߲㸺�𣬿��ܻᷢ��ʧ�ܣ�<br>
	 * ʧ�ܺ��֪��һ�㣻
	 * @param serviceList
	 * @return
	 */
	public boolean registryService(List<String> serviceList) {
		return conversation.registryService(serviceList);
	}
	
	/**
	 * ע�������ɸ߲㸺�𣬿��ܻᷢ��ʧ�ܣ�<br>
	 * ʧ�ܺ��֪��һ�㣻
	 * @param serviceList
	 * @return
	 */
	public boolean cancellationService(List<String> serviceList) {
		return conversation.cancellationService(serviceList);
	}
	
}
