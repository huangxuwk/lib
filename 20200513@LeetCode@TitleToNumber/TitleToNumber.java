package com.dl.test6;

public class TitleToNumber {

    public int titleToNumber(String s) {
        if (s.length() <= 0) {
            return 0;
        }
        char[] charA = s.toCharArray();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result = result * 26 + Integer.valueOf(charA[i] - 'A' + 1);
        }
        return result;
    }
	
}
