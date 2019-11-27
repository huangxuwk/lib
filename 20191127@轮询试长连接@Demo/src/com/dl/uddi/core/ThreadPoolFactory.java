package com.dl.uddi.core;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolFactory {
	private static volatile ThreadPoolExecutor threadPool;
	
	private ThreadPoolFactory() {
	}
	
	public static  void newInstance(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit) {
		if (threadPool == null) {
			synchronized (ThreadPoolFactory.class) {
				if (threadPool == null) {
					threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize
							, keepAliveTime, unit, new LinkedBlockingDeque<>());
				}
			}
		}
	}
	
	public static void execute(Runnable thread) {
		if (threadPool == null) {
			newInstance(10, 50, 5000, TimeUnit.MILLISECONDS);
		}
		threadPool.execute(thread);
	}
	
	public static void shutdown(boolean shutdownNow) {
		if (shutdownNow) {
			threadPool.shutdownNow();
		} else {
			threadPool.shutdown();
		}
	}
	
	
}
