package com.dl.test3;

public class LeftRotateString {

	public LeftRotateString() {
	}
	
    public String leftRotateString(String str,int n) {
        if (str == null || str.equals("") || n <= 0) {
            return str;
        }
        int length = str.length();
        int offset = n % length;
        
        String str1 = str.substring(offset);
        String str2 = str.substring(0, offset);
        return str1 + str2;
     }
    
    public static void main(String[] args) {
		System.out.println(new LeftRotateString().leftRotateString("abcdefg", 2));
	}
}
