package com.dl.client.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;

import com.util.ArgumentMaker;

public class RPCClient {
	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	private int port;
	private String ip;
	
	public RPCClient() {
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
	
	private void startup() {
		try {
			client = new Socket(ip, port);
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public <T> T remoteProcedureCall(Method method, Object[] args) {
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
			System.out.println("·µ»Ø½á¹û£º"  + str);
			T result = maker.fromJson(str, method.getReturnType());
		
			close();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
