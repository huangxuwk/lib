package com.mec.about_thread.core;

public class producer implements Runnable {

	public producer() {
		new Thread(this, "������").start();
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
					//�������˾���Ҫ�������Դ�����������������߳�
					Shared.lock.notifyAll();  
				}	
			} else {
				//����Ѿ����ˣ�����Ҫִ������Ĵ��룬�������ʱ��Ƭ�ι��ˣ�ȥִ���������̣߳�
				//Ҫ�������߰����������������ˣ��Ǿ�û�������ˣ����ǻع���ִ������Ĵ��룬
				//�ǾͻὫ�Լ��������������޷��������ݣ��������߳��ж������ݣ���ôҲ������Լ�����
				//����������ͻῨ��������취����ʱ���Խ��������ж�����������
				synchronized (Shared.lock) {
					try {
						//����Ѿ�׼�����ˣ���Ҫ�����������̣߳�����������
						Shared.lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		
	}

}
