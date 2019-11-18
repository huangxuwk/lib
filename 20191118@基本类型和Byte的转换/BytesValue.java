package com.util;

public class BytesToString {
	private static final String HEX_STR = "0123456789ABCDEF";
	
	public BytesToString() {
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
	
	public static String ByteToString(Byte b) {
		return String.valueOf(HEX_STR.charAt((b >>> 4) & 0x0F))
				+ String.valueOf(HEX_STR.charAt(b & 0x0F));
	}
	
	public static String ByteToString(Byte[] bytes) {
		StringBuffer str = new StringBuffer("");
		
		for (Byte b : bytes) {
			str.append(ByteToString(b));
		}
		
		return str.toString();
	}
	
	public static String ByteToString(Byte[] bytes, int length) {
		StringBuffer str = new StringBuffer("");
		int i = 0;
		for (Byte b : bytes) {
			str.append(ByteToString(b));
			i++;
			if (i % length == 0) {
				str.append("\n");
			}
		}
		
		return str.toString();
	}
	
	public static Byte StringByte(String str) {
		byte result = 0;
		result += (byte) HEX_STR.indexOf(str.substring(0, 0+1)) << 4;
		result += (byte) HEX_STR.indexOf(str.substring(1, 1+1));
		return result;
	}
	
	public static Byte[] StringToBytes(String str) {
		int length = str.length();
		if (length % 2 != 0) {
			return null;
		}
		length = length / 2;
		Byte[] bytes = new Byte[length];
		for (int i = 0; i < length; i++) {
			bytes[i] = StringByte(str.substring(2 * i, 2 * i + 2));
		}
		
		return bytes;
	}
	
	public static byte[] intToBytes(int value) {
		int len = 4;
		byte[] result = new byte[4];
		
		for (int i = 0; i < len; i++) {
			result[i] = (byte) ((value >> (8 * i)) & 0xFF);
		}
		
		return result;
	}
	
	public static int bytesToInt(byte[] bytes) {
		int len = 4;
		int result = 0;
		
		// java内存为高低低高原则
		for (int i = 0; i < len; i++) {
			result |= bytes[i] << (8 * i);
		}
		
		return result;
	}
	
	public static byte[] longToBytes(long value) {
		int len = 8;
		byte[] result = new byte[8];
		
		for (int i = 0; i < len; i++) {
			result[i] = (byte) ((value >> (8 * i)) & 0xFF);
		}
		
		return result;
	}
	
	public static long bytesToLong(byte[] bytes) {
		int len = 8;
		long result = 0;
		
		// java内存为高低低高原则
		for (int i = 0; i < len; i++) {
			result |= bytes[i] << (8 * i);
		}
		
		return result;
	}
	
	public static byte[] getBytesAt(byte[] value, int offset, int len) {
		byte[] result = new byte[len];
		
		for (int i = 0; i < len; i++) {
			result[i] = value[offset + i];
		}
		
		return result;
	}
	
	public static byte[] setBytesAt(byte[] target, int offset, byte[] resource) {
		for (int i = 0; i < resource.length; i++) {
			target[offset + i] = resource[i];
		}
		
		return target;
	}
	
}
