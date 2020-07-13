package com.jd.leetcode.textA;

public class BackspaceCompare {

    public boolean backspaceCompare(String S, String T) {
        return newString(S).equals(newString(T));
    }

    private String newString(String s) {
        char[] cs = s.toCharArray();
        int end = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '#') {
                if (end - 1 >= 0) {
                    end--;
                }
            } else {
                cs[end++] = cs[i];
            }
        }
        if (end == 0) {
            return "";
        }
        return new String(cs).substring(0, end);
    }

}
