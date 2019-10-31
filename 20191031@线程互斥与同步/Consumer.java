package com.mec.about_thread.core;

public class Consumer implements Runnable {

	public Consumer() {
		new Thread(this, "消费者").start();
	}
	
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		while (true) {
			if (Shared.ready) {
				System.out.println(name + ":" + Shared.data);
				Shared.ready = false;
				synchronized (Shared.lock) {
					Shared.lock.notifyAll();
				}
			} else {
				//这里与生产者线程所出现的问题的本质一致，都会由于时间片段的随机性导致程序崩溃
				synchronized (Shared.lock) {
					try {
						Shared.lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
 
}
