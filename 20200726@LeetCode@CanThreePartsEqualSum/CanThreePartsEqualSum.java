package com.dl.textL;

public class CanThreePartsEqualSum {

    public boolean canThreePartsEqualSum(int[] A) {
        int len = A.length;
        if (len == 0) {
            return false;
        }
        int sum = 0;
        for (int a : A) {
            sum += a;
        }
        if (sum % 3 != 0) {
            return false;
        }
        sum /= 3;
        int count = 0;
        int i = 0;
        int s = 0;
        while (i < len) {
            s += A[i];
            if (s == sum) {
                s = 0;
                count++;
            }
            i++;
        }
        return count == 3 || (count > 3 && sum == 0);
    }
	
}
