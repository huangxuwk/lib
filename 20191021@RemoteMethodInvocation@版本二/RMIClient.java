package com.dl.rmi.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;

import com.dl.util.ArgumentMaker;

public class RMIClient {
	private String rmiIp;
	private int rmiPort;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public RMIClient() {
	}

	public RMIClient(String rmiIp, int rmiPort) {
		this.rmiIp = rmiIp;
		this.rmiPort = rmiPort;
	}

	public void setRmiIp(String rmiIp) {
		this.rmiIp = rmiIp;
	}

	public void setRmiPort(int rmiPort) {
		this.rmiPort = rmiPort;
	}
	
	void connectToRMIServer() {
		try {
			this.socket = new Socket(rmiIp, rmiPort);
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	Object invokeMethod(Method method, Object[] args) throws Exception {
		Object result = null;
		try {
			// 向服务器发送接口方法的hashCode，此字符串在绝大多数保持唯一
			dos.writeUTF(String.valueOf(method.toString().hashCode()));
			// 判断参数是否有值
			if (args != null) {
				ArgumentMaker maker = new ArgumentMaker();
				int index = 0;
				for (Object object : args) {
					maker.addArg("arg" + index, object);
					index++;
				}
				dos.writeUTF(maker.toString());
			} else {
				dos.writeUTF("");
			}

			String res = dis.readUTF();
			close();
			if (res.equals("ERROR")) {
				throw new Exception("未找到该方法！");
			}
			result = ArgumentMaker.gson.fromJson(res, method.getReturnType());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private void close() {
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
		try {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			socket = null;
		}
	}
}
