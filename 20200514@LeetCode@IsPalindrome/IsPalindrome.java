package com.dl.test6;

public class IsPalindrome {

    public boolean isPalindrome(String s) {
    	if (s.length() == 0) {
    		return true;
    	}
    	String str = new String(s.replaceAll("[^0-9a-zA-Z]", ""));
		return str.equalsIgnoreCase(new StringBuilder(str).reverse().toString());
    }
	
}
