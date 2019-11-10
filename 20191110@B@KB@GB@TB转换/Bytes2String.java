package com.dl.about_io.test;

public class Bytes2String {

	public Bytes2String() {
	}
	
	public static String bytesToString(long size) {
		/*
		 * 1L : long ���͵ģ��Է�ֹ��������50λ����4�ֽڷ�Χ
		 * ��int��ǿת��Ϊ���ú����������������ȥС��������
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
