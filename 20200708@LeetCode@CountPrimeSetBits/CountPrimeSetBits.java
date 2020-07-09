package com.dl.textJ;

public class CountPrimeSetBits {

    public int countPrimeSetBits(int L, int R) {
        if (L >= R) {
            return 0;
        }
        int count = 0;
        for (int i = L; i <= R; i++) {
            if (isPrime(Integer.bitCount(i))) {
                count++;
            }
        }
        return count;
    }

    // 2^20 > 10^6
    private boolean isPrime(int n) {
        if (n == 2 || n == 3 || n == 5 || n == 7
            || n == 11 || n == 13 || n == 17 || n == 19) {
                return true;
        }
        return false;
    }
	
}
