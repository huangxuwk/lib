package com.dl.test5;

public class MySqrt {

    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        long left = 0;
        long right = x;
        while (left < right) {
            long mid = (left + right + 1) >>> 1;
            long sql = mid * mid;
            if (sql > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return (int)left;
    }
    
}
