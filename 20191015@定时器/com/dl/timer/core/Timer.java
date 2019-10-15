package com.dl.timer.core;

public class Timer implements Runnable {
	// Ĭ�ϵȴ�ʱ��
	private static final int DEFAULT_DALEY_TIME = 1000;
	// ��ʱ��ʱ��
	private long delayTime;
	// ÿ���������������Ӧ����ͬ
	private Object lock;
	// ��־�������Կ����߳��Ƿ��������
	private volatile boolean goon;
	// �˽ӿڶ����û���Ҫ��ʱִ�еķ���
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
			throw new Exception("�û�����δ���壡");
		}
		goon = true;
		// ���������̣߳�һ���Ƕ�ʱ���̣߳�����ʱ
		// һ����ִ���û��������߳�
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
					// �����ȴ���wait()����������ڶ�����
					lock.wait(delayTime);
					// �ȴ���ɣ�������Ҫִ���û��������߳�
					lock.notify();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class Action implements Runnable {
		// ���̵߳������ǣ����ȴ�ʱ�䵽��֮�󣬶�ʱ�̻߳��Ѵ��߳�
		// ���߳̿�ʼִ���û�����
		@Override
		public void run() {
			while (goon) {
				synchronized (lock) {
					try {
						// �˷���ִ�к�jvm���ỽ�ѣ����ɱ���߳�������
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// ���̱߳����ѣ���ִ�д˷�����ִ���꣬����������
				userAction.userAction();
			}
		}
	}
	
}
