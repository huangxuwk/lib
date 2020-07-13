package com.jd.leetcode.textA;

public class MaxDistToClosest {

    public int maxDistToClosest(int[] seats) {
        int len = seats.length;
        if (len == 0) {
            return 0;
        }
        // 统计数组前面连续的0
        int max = 0;
        for (int i = 0; i < len; i++) {
            if (seats[i] == 1) {
                if (i > max) {
                    max = i;
                }
                break;
            }
        }
        int pre = -1;
        for (int i = 0; i < len; i++) {
            if (seats[i] == 1) {
                if (pre == -1) {
                    pre = i;
                } else {
                    if ((i - pre) / 2 > max) {
                        max = (i - pre) / 2;
                    }
                    pre = i;
                }
            }
        }
        // 统计数组后面的连续0
        for (int i = len - 1; i >= 0; i--) {
            if (seats[i] == 1) {
                if (len - i - 1 > max) {
                    max = len - i - 1;
                }
                break;
            }
        }
        return max;
    }

}
