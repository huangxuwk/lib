package com.dl.timer.core;

public class Timer implements Runnable {
	// 默认等待时间
	private static final int DEFAULT_DALEY_TIME = 1000;
	// 定时的时长
	private long delayTime;
	// 每个对象产生的锁不应该相同
	private Object lock;
	// 标志符，用以控制线程是否开启或结束
	private volatile boolean goon;
	// 此接口定义用户需要定时执行的方法
	private UserAction userAction;
	
	public Timer() {
		this(DEFAULT_DALEY_TIME);
	}

	public Timer(long delayTime) {
		this(delayTime, null);
		this.delayTime = delayTime;
	}

	public Timer(long delayTime, UserAction userAction) {
		lock = new Object();
		this.delayTime = delayTime;
		this.userAction = userAction;
	}

	public long getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(long delayTime) {
		this.delayTime = delayTime;
	}

	public void setUserAction(UserAction userAction) {
		this.userAction = userAction;
	}

	public void startThread() throws Exception {
		if (goon == true) {
			return;
		}
		if (userAction == null) {
			throw new Exception("用户方法未定义！");
		}
		goon = true;
		// 开启两个线程，一个是定时器线程，负责定时
		// 一个是执行用户方法的线程
		new Thread(new Action(), "action").start();;
		new Thread(this, "timer").start();
	}
	
	public void stopThread() {
		if (goon == false) {
			return;
		}
		goon = false;
	}
	
	@Override
	public void run() {
		while (goon) {
			synchronized (lock) {
				try {
					// 阻塞等待，wait()方法必须基于对象锁
					lock.wait(delayTime);
					// 等待完成，则唤醒需要执行用户方法的线程
					lock.notify();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class Action implements Runnable {
		// 此线程的作用是，当等待时间到了之后，定时线程唤醒此线程
		// 此线程开始执行用户方法
		@Override
		public void run() {
			while (goon) {
				synchronized (lock) {
					try {
						// 此方法执行后，jvm不会唤醒，而由别的线程来唤醒
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 此线程被唤醒，则执行此方法，执行完，又自身阻塞
				userAction.userAction();
			}
		}
	}
	
}
