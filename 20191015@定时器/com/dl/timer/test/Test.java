package com.dl.timer.test;

import com.dl.timer.core.Timer;
import com.dl.timer.core.UserAction;

public class Test {
		
		public static void main(String[] args) {
			Timer timer = new Timer(200, new UserAction() {
				@Override
				public void userAction() {
					System.out.println(System.currentTimeMillis());
				}
			});
			try {
				timer.startThread();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
