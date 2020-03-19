package com.dl.test3;

public class LongestPalindrome {

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 0) {
            return "";
        }
        int start = 0;
        int end = 0;
        for (int index = 0; index < s.length() - 1; index++) {
            // 奇数回文
            int len1 = getLen(s, index, index);
            // 偶数回文
            int len2 = getLen(s, index, index + 1);
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                start = index - (len - 1) / 2;
                end = index + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int getLen(String s, int left, int right) {
        int le = left;
        int ri = right;
        while (le >= 0 && ri < s.length() && s.charAt(le) == s.charAt(ri)) {
            le--;
            ri++;
        }
        return ri - le - 1;
    }
}
