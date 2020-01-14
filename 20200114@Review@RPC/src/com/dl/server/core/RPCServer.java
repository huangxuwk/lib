package com.dl.server.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;

import com.util.ArgumentMaker;
import com.util.ThreadPoolFactory;

public class RPCServer implements Runnable {
	private ServerSocket server;
	private int port;
	
	private volatile boolean goon;
	
	public RPCServer() {
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void startup() {
		try {
			server = new ServerSocket(port);
			goon = true;
			
			ThreadPoolFactory.execute(new Thread(this));
			System.out.println("服务器已启动");
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
	}

	@Override
	public void run() {
		while (goon) {
			try {
				Socket socket = server.accept();
				
				InnerInvoke inner = new InnerInvoke();
				inner.setClient(socket);
				ThreadPoolFactory.execute(inner);
				System.out.println("已检测到连接");
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
			// TODO  接收方法和参数，执行方法并返回
			try {
				String method = dis.readUTF();
				int hashCode = Integer.valueOf(method);
				String argsStr = dis.readUTF();
				ArgumentMaker maker = new ArgumentMaker(argsStr);
				
				MethodDefinition methodDefinition = MethodFactory.getMethodDefinition(hashCode);
				if (methodDefinition == null) {
					System.out.println("没有方法");
					dos.writeUTF("null");
				} else {
					Object result = invokeMethod(methodDefinition, maker);
					String resultStr = maker.toJson(result);
					System.out.println("执行结果：" + resultStr);
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
