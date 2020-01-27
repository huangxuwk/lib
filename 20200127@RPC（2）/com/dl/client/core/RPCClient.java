package com.dl.client.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;

import com.parser_reflect.util.PropertiesParser;
import com.util.ArgumentMaker;

/**
 * RPC客户端<br>
 * 1、提供连接服务器的方法；<br>
 * 2、支持服务器的ip、port配置；<br>
 * 3、提供实现负载均衡的接口；
 * 
 * @author dl
 *
 */
public class RPCClient {
	public static final String DEFAULT_IP = "127.0.0.1";
	public static final int DEFAULT_PORT = 55555;
	
	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	private int port;
	private String ip;
	
	private ServerBalance serverBalance;
	
	public RPCClient() {
		this.ip = DEFAULT_IP;
		this.port = DEFAULT_PORT;
		readConfig("/RPCConfig.properties");
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
	
	public void setServerBalance(ServerBalance serverBalance) {
		this.serverBalance = serverBalance;
	}
	
	public void readConfig(String path) {
		PropertiesParser.load(path);
		
		String ip = PropertiesParser.findElement("RPCIp");
		if (ip != null && !ip.equals("")) {
			this.ip = ip;
		}
		String portStr = PropertiesParser.findElement("RPCPort");
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
	
	private void startup() throws UnknownHostException, IOException {
		if (serverBalance != null) {
			ServerNetNode node = serverBalance.getServerNode();
			this.ip = node.getIp();
			this.port = node.getPort();
		}
		client = new Socket(ip, port);
		dis = new DataInputStream(client.getInputStream());
		dos = new DataOutputStream(client.getOutputStream());
	}
	
	private void close() {
		try {
			if (!client.isClosed() && client != null) {
				client.close();		
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			client = null;
		}
		try {
			if (dis != null) {
				dis.close();		
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dis = null;
		}
		try {
			if (dos != null) {
				dos.close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dos = null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T remoteProcedureCall(Method method, Object[] args) throws UnknownHostException, IOException {
		startup();
		int hashCode = method.toString().hashCode();
		ArgumentMaker maker = new ArgumentMaker();
		try {
			dos.writeUTF(String.valueOf(hashCode));
			if (args == null) {
				dos.writeUTF("");
			} else {
				int index = 0;
				for (Object object : args) {
					maker.addArg("arg" + index++, object);
				}
				dos.writeUTF(maker.toString());
			}
			String str = dis.readUTF();
			T result = (T) ArgumentMaker.gson.fromJson(str, method.getReturnType());
			close();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
