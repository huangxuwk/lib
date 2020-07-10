package com.jd.leetcode.textA;

public class RotateString {

    public boolean rotateString(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        String s = A + A;
        if (s.contains(B)) {
            return true;
        } else {
            return false;
        }
    }

}
