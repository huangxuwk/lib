package com.dl.test7;

public class CountPrimes {

    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        // 2是质数
        int count = 1;
        boolean[] primes = new boolean[n];
        // 除了2，质数都不是偶数
        for (int i = 3; i < n; i+=2) {
            // 如果是质数，把这个质数的奇数倍都变为非质数
            if (!primes[i]) {
                for (int j = 3; j * i < n; j+=2) {
                    primes[i * j] = true;
                }
                count++;
            }
        }
        return count;
    }
	
}
