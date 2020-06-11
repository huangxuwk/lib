package com.dl.text1;

public class LongestPalindrome {
    public int longestPalindrome(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int[] ca = new int[58];
        char[] sc = s.toCharArray();
        for (char c : sc) {
            ca[c - 'A']++;
        }
        int count = 0;
        for (int i : ca) {
            // ������������һ���м���
            count += i % 2; 
        }
        
        return count == 0 ? sc.length : (sc.length - count + 1);
    }
}
