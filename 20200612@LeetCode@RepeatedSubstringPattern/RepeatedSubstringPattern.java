package com.dl.text1;

public class RepeatedSubstringPattern {

    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
	
}
