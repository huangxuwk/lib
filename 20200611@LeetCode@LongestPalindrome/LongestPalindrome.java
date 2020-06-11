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
            // 计算奇数个数一共有几个
            count += i % 2; 
        }
        
        return count == 0 ? sc.length : (sc.length - count + 1);
    }
}
