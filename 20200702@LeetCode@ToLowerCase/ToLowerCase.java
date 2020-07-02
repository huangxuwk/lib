package com.dl.textH;

public class ToLowerCase {

    public String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();
        char[] sc = str.toCharArray();
        for (char c : sc) {
            if (c <= 'Z' && c >= 'A') {
                sb.append((char)(c + 32));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
	
}
