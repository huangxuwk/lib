package com.dl.textK;

public class DiStringMatch {

    public int[] diStringMatch(String S) {
        int sLen = S.length();
        int[] arr = new int[sLen + 1];
        int max = sLen;
        int min = 0;
        for(int i = 0 ; i < sLen; i++) {
            if('D' == S.charAt(i)) {
                arr[i] = max--;
            } else {
                arr[i] = min++;
            }
        }
        arr[sLen] = max;
        return arr;
    }
	
}
