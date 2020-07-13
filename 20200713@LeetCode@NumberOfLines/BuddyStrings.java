package com.jd.leetcode.textA;

public class BuddyStrings {

    public boolean buddyStrings(String A, String B) {
        int len1 = A.length();
        int len2 = B.length();
        if (len1 != len2 || len1 == 0) {
            return false;
        }
        char[] ac = A.toCharArray();
        char[] bc = B.toCharArray();
        int count = 0;
        int[] ns = new int[26];
        char[] a = new char[2];
        char[] b = new char[2];
        boolean repeat = false;
        for (int i = 0; i < len1; i++) {
            if (ac[i] != bc[i]) {
                if (count + 1 > 2) {
                    return false;
                }
                a[count] = ac[i];
                b[count++] = bc[i];
            } else {
                if (++ns[ac[i] - 'a'] > 1) {
                    repeat = true;
                }
            }
        }
        if (a[0] != b[1] || a[1] != b[0]) {
            return false;
        }
        if (count == 0 && !repeat) {
            return false;
        }
        return true;
    }

}
