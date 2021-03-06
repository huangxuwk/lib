package com.dl.client.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.swing.JFrame;

/**
 * 代理类<br>
 * 1、为RPC调用做准备，客户端只用保存接口；<br>
 * 2、通过接口得到代理对象来调用方法；<br>
 * 3、在方法中进行拦截，拦截时连接服务器进行远程方法调用；<br>
 * 4、支持模态框的开启；
 * 
 * @author dl
 *
 */
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
	
	/**
	 * 模态框的管理类
	 * 1、实现了Callable接口，可接受线程方法的返回值；
	 * 2、模态框开启后会阻塞当前线程，因此模态框的开启与关闭需要在两个线程中进行；
	 * 3、将远程方法的调用放在此线程中，接收结果后关闭模态框；
	 * 
	 * @author dl
	 *
	 */
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
			Object result = null;
			try {
				result = client.remoteProcedureCall(method, args);
			} finally {
				// 无论发生什么，必须关闭模态框
				dialog.close();
			}
			return result;
		}
		
	}
}
