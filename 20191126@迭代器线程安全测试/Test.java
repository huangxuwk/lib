package com.dl.itertor_sych.test;

import java.util.ArrayList;
import java.util.List;

/*
 * ����ʹ�õ�����ʱ����ԭ��collection��������κε����ɾ��ʱ
 * ������� ͬʱ�޸��쳣
 */
public class Test {
	public static final List<Integer> intPool;
	
	static {
		intPool = new ArrayList<>();
		intPool.add(1);
		intPool.add(2);
		intPool.add(3);
		intPool.add(4);
	}
	
		public static void main(String[] args) {
			new Thread(new ThreadOne()).start();
		}

		static class ThreadOne implements Runnable {

			@Override
			public void run() {
				new Thread(new ThreadTwo()).start();
				for (int num : intPool) {
					System.out.println("˯��");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(num);
				}
			}
		
			class ThreadTwo implements Runnable {

				@Override
				public void run() {
					intPool.add(6);
					System.out.println("�Ѽ���6");
				}
				
			}
		}
}
