package com.dl.test9;

public class IsPowerOfThree {
    // private static final Set<Integer> set;
    // static {
    //     set = new HashSet<Integer>();
    //     int v = 1;
    //     while (v <= Integer.MAX_VALUE) {
    //         set.add(v);
    //         v *= 3;
    //     }
    // }

    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467%n == 0;
    }
}
