package com.dl.text3;

public class CheckRecord {

    public boolean checkRecord(String s) {
        if (s.length() == 0) {
            return true;
        }
        char[] sc = s.toCharArray();
        int ASize = 0;
        int LSize = 0;
        char pre = 'z';
        for (char c : sc) {
            if (c == 'A' && ++ASize > 1) {
                return false;
            }
            if (c == 'L' && pre == 'L' && ++LSize > 2) {
                return false;
            } else if (c == 'L' && pre != 'L') {
                LSize = 1;
            }
            pre = c;
        }
        return true;
    }
	
}
