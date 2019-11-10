package com.dl.about_io.test;

public class Bytes2String {

	public Bytes2String() {
	}
	
	public static String bytesToString(long size) {
		/*
		 * 1L : long 类型的，以防止后面右移50位超出4字节范围
		 * （int）强转是为了让后面数的算数结果舍去小数点后面的
		 */
		if (size < (1L << 10)) {
			return String.valueOf(size) + "B";
		}
		if (size < (1L << 20)) {
			return String.valueOf(size >> 10) + "." +
					String.valueOf((int) (size & 0x3FF) / 1024.0
							* 1000 + 1000).substring(1) + "KB";
		}
		if (size < (1L << 30)) {
			return String.valueOf(size >> 20) + "." +
					String.valueOf((int) (size & 0xFFFFF) / (1024.0*1024) 
							* 1000 + 1000).substring(1) + "MB";
		}
		if (size < (1L << 40)) {
			return String.valueOf(size >> 30) + "." +
					String.valueOf((int) ((size & 0x3FFFFFFF) / (1024.0*1024*1024) 
							* 1000 + 1000)).substring(1) + "GB";
		}
		if (size < (1L << 50)) {
			return String.valueOf(size >> 40) + "." +
					String.valueOf((int) ((size & 0xFFFFFFFFFFL) / (1024.0*1024*1024*1024)
							* 1000 + 1000)).substring(1) + "TB";
		}

		
		
		return null;
	}
}
