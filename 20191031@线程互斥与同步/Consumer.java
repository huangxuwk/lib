package com.mec.about_thread.core;

public class Consumer implements Runnable {

	public Consumer() {
		new Thread(this, "������").start();
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
				//�������������߳������ֵ�����ı���һ�£���������ʱ��Ƭ�ε�����Ե��³������
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
