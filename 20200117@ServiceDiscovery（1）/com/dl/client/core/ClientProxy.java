package com.dl.client.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.swing.JFrame;

public class ClientProxy {
	private RPCClient client;
	
	public ClientProxy() {
	}
	
	public void setClient(RPCClient client) {
		this.client = client;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T jdkProxy(Class<?> interfaces) {
		ClassLoader classLoader = interfaces.getClassLoader();
		return (T) Proxy.newProxyInstance(classLoader, new Class<?>[]{interfaces}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return client.remoteProcedureCall(method, args);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public <T> T jdkProxy(Class<?> interfaces, JFrame parent) {
		ClassLoader classLoader = interfaces.getClassLoader();
		return (T) Proxy.newProxyInstance(classLoader, new Class<?>[]{interfaces}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				ClientDialog dialog = new ClientDialog();
				DialogManager manager = new DialogManager(dialog, method, args);
				FutureTask<Object> future = new FutureTask<>(manager);
				new Thread(future).start();
				dialog.showMessageDialog(parent, "提示", "正在处理，请稍后！");
				
				return future.get();
			}
		});
	}
	
	private class DialogManager implements Callable<Object> {
		private ClientDialog dialog;
		private Method method;
		private Object[] args;
		
		public DialogManager(ClientDialog dialog, Method method, Object[] args) {
			this.dialog = dialog;
			this.method = method;
			this.args = args;
		}
		
		@Override
		public Object call() throws Exception {
			Object result = client.remoteProcedureCall(method, args);
			dialog.close();
			return result;
		}
		
	}
}
