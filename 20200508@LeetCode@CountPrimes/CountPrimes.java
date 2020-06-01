package com.dl.test7;

public class CountPrimes {

    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        // 2������
        int count = 1;
        boolean[] primes = new boolean[n];
        // ����2������������ż��
        for (int i = 3; i < n; i+=2) {
            // ��������������������������������Ϊ������
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
