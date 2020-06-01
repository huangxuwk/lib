package com.dl.test7;

public class ReverseBits {

    public int reverseBits(int n) {
        int result = 0;
        for (int i = 31; i >= 0; i--) {
            result += (n & 1) << i;
            n = n >>> 1;
        }
        return result;
    }
	
}
