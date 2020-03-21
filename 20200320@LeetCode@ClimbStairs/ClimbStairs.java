package com.dl.test3;

public class ClimbStairs {

    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }
        int first = 0;
        int secend = 1;
        int thrid = 0;
        for (int i = 1; i <= n; i++) {
            thrid = first + secend;
            first = secend;
            secend = thrid;
        }
        return thrid;
    }
}
