package com.jd.leetcode.textA;

public class NumberOfLines {

    public int[] numberOfLines(int[] widths, String S) {
        int[] re = new int[2];
        if (widths.length == 0 || S.length() == 0) {
            return re;
        }
        char[] cs = S.toCharArray();
        int line = 0;
        int len = 0;
        for (char c : cs) {
            int l = widths[c - 'a'];
            if (l + line > 100) {
                len += 100 - line;
                line = l;
            } else {
                line = (line + l) % 100;
            }
            len += l;
        }
        re[0] = len % 100 == 0 ? len / 100 : len / 100 + 1;
        re[1] = len % 100;
        return re;
    }

}
