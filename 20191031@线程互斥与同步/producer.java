package com.mec.about_thread.core;

public class producer implements Runnable {

	public producer() {
		new Thread(this, "生产者").start();
	}
	
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		while (true) {
			if (!Shared.ready) {
				Shared.data = (int) (Math.random() * 1000);
				System.out.println(name + ":" + Shared.data);
				Shared.ready = true;
				synchronized (Shared.lock) {
					//生产好了就需要唤醒所以处于这个锁里阻塞的线程
					Shared.lock.notifyAll();  
				}	
			} else {
				//如果已经好了，就需要执行这里的代码，但是如果时间片段够了，去执行消费者线程，
				//要是消费者把生产的数据消费了，那就没有数据了，但是回过来执行这里的代码，
				//那就会将自己阻塞，阻塞完无法生产数据，消费者线程判断无数据，那么也将会把自己阻塞
				//这样，程序就会卡死；解决办法：暂时可以将锁放在判断条件的外面
				synchronized (Shared.lock) {
					try {
						//如果已经准备好了，就要阻塞生产者线程，即自我阻塞
						Shared.lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		
	}

}
