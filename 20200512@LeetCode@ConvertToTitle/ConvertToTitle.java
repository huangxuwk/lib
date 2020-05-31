package com.dl.test6;

public class ConvertToTitle {

    public String convertToTitle(int n) {
        if (n < 1) {
            return "";
        }
        StringBuilder bu = new StringBuilder();
        while (n > 0) {
            n--;
            bu.append((char)(n % 26 + 'A'));
            n /= 26;
        }
        return bu.reverse().toString();
    }
	
}
