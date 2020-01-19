package com.dl.server.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.parser_reflect.util.PropertiesParser;
import com.util.ArgumentMaker;

public class RPCServer implements Runnable {
	public static final int DEFAULT_PORT = 55555;
	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 100, 
			5000, TimeUnit.MILLISECONDS, 
			new LinkedBlockingDeque<>());
	
	private ServerSocket server;
	private int port;
	
	private volatile boolean goon;
	
	public RPCServer() {
		readConfig("/RPCConfig.properties");
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void readConfig(String path) {
		PropertiesParser.load(path);
		
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
	
	public void startup() {
		try {
			server = new ServerSocket(port);
			goon = true;
			threadPool.execute(new Thread(this));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		goon = false;
		try {
			if (!server.isClosed() && server != null) {
				server.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			server = null;
		}
		threadPool.shutdown();
	}

	@Override
	public void run() {
		while (goon) {
			try {
				Socket socket = server.accept();
				InnerInvoke inner = new InnerInvoke();
				inner.setClient(socket);
				threadPool.execute(inner);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class InnerInvoke implements Runnable {
		private DataInputStream dis;
		private DataOutputStream dos;
		
		public InnerInvoke() {
		}
		
		public void setClient(Socket client) {
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
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
		}
		
		@Override
		public void run() {
			try {
				String method = dis.readUTF();
				int hashCode = Integer.valueOf(method);
				String argsStr = dis.readUTF();
				ArgumentMaker maker = new ArgumentMaker(argsStr);
				MethodDefinition methodDefinition = MethodFactory.getMethodDefinition(hashCode);
				if (methodDefinition == null) {
					dos.writeUTF("null");
				} else {
					Object result = invokeMethod(methodDefinition, maker);
					String resultStr = ArgumentMaker.gson.toJson(result);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					dos.writeUTF(resultStr);
				}
				close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private Object invokeMethod(MethodDefinition methodDefinition, ArgumentMaker maker) {
			Object object = methodDefinition.getObject();
			Method method = methodDefinition.getMethod();
			
			Parameter[] parameters = method.getParameters();
			int size = parameters.length;
			Object[] args = new Object[size];
			for (int index = 0; index < size; index++) {
				args[index] = maker.getValue(
							"arg" + index, 
							parameters[index].getParameterizedType());
			}
			
			try {
				return method.invoke(object, args);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
}
