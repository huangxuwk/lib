package com.dl.text1;

import java.util.Arrays;

public class FindContentChildren {

    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int index1 = 0;
        int index2 = 0;
        int count = 0;
        while (index1 < g.length && index2 < s.length) {
            if (s[index2++] >= g[index1]) {
                count++;
                index1++;
            }
        }
        return count;
    }
	
}
