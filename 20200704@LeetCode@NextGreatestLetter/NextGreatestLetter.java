package com.dl.textI;

public class NextGreatestLetter {

    public char nextGreatestLetter(char[] letters, char target) {
        int min = Integer.MAX_VALUE;
        char re = target;
        for (char c : letters) {
            if (c == target) {
                continue;
            }
            int del = (int)(c - target);
            if (del > 0 && (del < min || min < 0)) {
                min = del;
                re = c;
            } else if (del < 0 && min < 0 && del < min) {
                min = del;
                re = c;
            } else if (min == Integer.MAX_VALUE && del < 0) {
                min = del;
                re = c;
            }
        }
        return re;
    }
	
}
