package com.dl.test10;

public class FindTheDifference {

    public char findTheDifference(String s, String t) {
        int[] ic = new int[26];
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        for (char c : ss) {
            ic[c - 'a']++;
        }
        for (char c : ts) {
            if (--ic[c - 'a'] < 0) {
                return c;
            }
        }
        return ' ';
    }
	
}
