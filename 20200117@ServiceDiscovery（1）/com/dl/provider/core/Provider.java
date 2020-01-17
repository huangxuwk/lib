package com.dl.provider.core;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import com.dl.registry.core.EMessageType;
import com.dl.registry.core.NetMessage;
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
	
	private VocationalWork work;
	
	public Provider() {
		readConfig("/RPCConfig.properties");
		this.ip = DEFAULT_IP;
		this.port = DEFAULT_PORT;
	}

	public int getPort() {
		return port;
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
	
	public void startup() {
		try {
			client = new Socket(ip, port);
			work = new VocationalWork(client);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		work.close();
	}
	
	private String serviceMessage(List<String> serviceList) {
		StringBuffer sb = new StringBuffer();
		boolean ok = false;
		for (String service : serviceList) {
			if (!ok) {
				sb.append(",").append(service);
				ok = true;
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * ע������ɸ߲㸺�𣬿��ܻᷢ��ʧ�ܣ�<br>
	 * ���û�����ע����������ʧ�ܺ�Ĳ�����
	 * @param serviceList
	 * @return
	 */
	public boolean registryService(List<String> serviceList) {
		NetMessage netMessage = new NetMessage()
				.setType(EMessageType.REGISTRY)
				.setParamater(serviceMessage(serviceList));
		
		return work.sendMessage(netMessage);
	}
	
	/**
	 * ע�������ɸ߲㸺�𣬿��ܻᷢ��ʧ�ܣ�<br>
	 * ���û�����ע����������ʧ�ܺ�Ĳ�����
	 * @param serviceList
	 * @return
	 */
	public boolean cancellationService(List<String> serviceList) {
		NetMessage netMessage = new NetMessage()
				.setType(EMessageType.CANCELLATION)
				.setParamater(serviceMessage(serviceList));
		
		return work.sendMessage(netMessage);
	}
	
}
