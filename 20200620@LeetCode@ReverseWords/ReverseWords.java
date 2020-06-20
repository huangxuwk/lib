package com.dl.text4;

public class ReverseWords {

    public String reverseWords(String s) {
        if (s.length() == 0) {
            return s;
        }
        String[] sa = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String str : sa) {
            sb.append(new StringBuilder(str).reverse().toString())
            .append(" ");
        }
        return sb.toString().trim();
    }
	
}
