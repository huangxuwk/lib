package com.dl.rmi.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.Socket;

import com.dl.util.ArgumentMaker;

public class RMIServerAcitoner implements Runnable {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;

	public RMIServerAcitoner(Socket socket) {
		this.socket = socket;
		
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			
			new Thread(this).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	@Override
	public void run() {
		try {
			String methodName = dis.readUTF();
			String argsString = dis.readUTF();
			// 根据methodName, 不但要找到方法，还要找到类
			// 真正执行method， 并返回值
			RMIMethodFactory factory = new RMIMethodFactory();
			RMIMethodDefinition methodDefinition = factory.getMethodDefinition(methodName);
			if (methodDefinition == null) {
				dos.writeUTF("ERROR");
				close();
				return;
			}
			Object result = invokeMethod(methodDefinition, argsString);
			dos.writeUTF(ArgumentMaker.gson.toJson(result));
			// 一次通信，就要关闭通信信道
			close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 对得到的参数进行解析，并执行方法得到返回值
	private Object invokeMethod(RMIMethodDefinition methodDefinition, String argsString) {
		ArgumentMaker maker = new ArgumentMaker(argsString);
		
		Method method = methodDefinition.getMethod();
		Object object = methodDefinition.getObject();
		Parameter[] parameters = method.getParameters();
		int length = parameters.length;
		Object[] args = new Object[length];
		for (int index = 0; index < length; index++) {
			String argsName = "arg" + index;
			// getParameterizedType()方法可以处理参数为泛型的情况
			// 在gson Map中找到对应的参数
			args[index] = maker.getValue(argsName, parameters[index].getParameterizedType());
		}
		Object result = null;
		try {
			result = method.invoke(object, args);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}

	

