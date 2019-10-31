package com.mec.about_thread.core;

public class TwoRelationThread implements Runnable {
	private static final Object lock = new Object();
	private static int num;
	
	public TwoRelationThread(String threadName) {
		//Ҫ��ʹ��ʵ�ֽӿڵķ����ͱ��봴���̶߳���������
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
