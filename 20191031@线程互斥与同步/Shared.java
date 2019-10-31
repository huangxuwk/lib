package com.mec.about_thread.core;

public class Shared {
	public static int data;
	public static boolean ready = false;
	public final static Object lock = new Object();
}
