package com.dl.itertor_sych.test;

import java.util.ArrayList;
import java.util.List;

/*
 * 关于使用迭代器时，对原有collection对象进行任何的添加删除时
 * 都会出现 同时修改异常
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
					System.out.println("睡眠");
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
					System.out.println("已加入6");
				}
				
			}
		}
}
