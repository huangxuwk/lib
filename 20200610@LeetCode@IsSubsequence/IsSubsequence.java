package com.dl.test10;

public class IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();        
        if (len1 == 0) {
            return true;
        }
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        int si = 0;
        int ti = 0;
        while (si < len1 && ti < len2) {          
            if (ss[si] == ts[ti]) {          
                si++;
                ti++;
            } else {
                ti++;
            }
        }
        if (si < len1) {
            return false;
        }
        return true;
    }
	
}
