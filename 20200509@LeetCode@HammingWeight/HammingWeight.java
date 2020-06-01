package com.dl.test7;

public class HammingWeight {

    public int hammingWeight(int n) {
        if (n == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < 32; i++, n >>>= 1) {
            if ((n & 1) == 1) {
                count++;
            }
        } 
        return count; 
    }
	
}
