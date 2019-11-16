package com.mec.util;

public class ByteString {
	public static final String HEX_STR = "0123456789ABCDEF";

	public ByteString() {
	}

	public static String byteToString(byte value) {
		return "" + HEX_STR.charAt((value >> 4) & 0x0F) 
				+ HEX_STR.charAt(value & 0x0F);
	}
	
	public static String bytesToString(byte[] bytes) {
		StringBuffer res = new StringBuffer();
		
		for (int i = 0; i < bytes.length; i++) {
			res.append(byteToString(bytes[i]));
		}
		
		return res.toString();
	}
	
	public static String bytesToString(byte[] bytes, int maxColumn) {
		StringBuffer res = new StringBuffer();
		
		for (int i = 0; i < bytes.length; i++) {
			res.append(byteToString(bytes[i]));
			if ((i+1) * 2 % maxColumn == 0) {
				res.append("\n");
			}
		}
		
		return res.toString();
	}
	
	public static Byte stringToByte(String str) {
		if (str == null || str.length() > 2) {
			return null;
		}
		str = str.length() == 1 ? ("0" + str) : str;
		
		byte result = 0;
		result += (byte) (HEX_STR.indexOf(str.substring(0, 1)) << 4);
		result += (byte) HEX_STR.indexOf(str.substring(1, 1+1));
		
		return result;
	}
	
	public static byte[] stringToBytes(String str) {
		int len = str.length();
		if (len % 2 != 0) {
			return null;
		}
		byte[] result = new byte[len / 2];
		for (int i = 0; i < len / 2; i++) {
			result[i] = stringToByte(str.substring(2*i, 2*i+2));
		}
		
		return result;
	}
	
}
