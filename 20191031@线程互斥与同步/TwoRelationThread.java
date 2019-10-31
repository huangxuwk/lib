package com.mec.about_thread.core;

public class TwoRelationThread implements Runnable {
	private static final Object lock = new Object();
	private static int num;
	
	public TwoRelationThread(String threadName) {
		//要想使用实现接口的方法就必须创建线程对象来调用
		new Thread(this, threadName).start();
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		int n;
		for (int i = 0; i < 100; i++) {
			synchronized (lock) {
				n = num;
				n = n + 30;
				n = n + 1;
				n = n - 30;
				num = n;
				System.out.println(name + ":" + num);	
			}		
		}
	}

}
