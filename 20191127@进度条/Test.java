package com.dl.progress.test;

import com.mec.mmfct.view.ITestProgressBar;
import com.mec.mmfct.view.TestForProgressBar;

public class Test {

	public static void main(String[] args) {
		TestForProgressBar tfpb = new TestForProgressBar();
		tfpb.showView();
		
		InnerTest test = new InnerTest(tfpb);
		new Thread(test).start();
	}

	public static class InnerTest implements Runnable {
		ITestProgressBar test;
		
		public InnerTest(ITestProgressBar test) {
			this.test = test;
		}
		
		@Override
		public void run() {
			for (int i = 0; i <= 100; i++) {
				try {
					Thread.sleep(200);
					test.change(i, 100);
				} catch (InterruptedException e) {
				}
			}
		}
		
	}
	
}
