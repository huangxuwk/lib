package com.jd.leetcode.textA;

public class PeakIndexInMountainArray {

    public int peakIndexInMountainArray(int[] A) {
        int len = A.length;
        if (len == 0) {
            return 0;
        }
        int max = -1;
        int index = -1;
        for (int i = 1; i < len; i++) {
            if (A[i] > max) {
                max = A[i];
                index = i;
            }
        }
        int last = max;
        for (int i = index - 1; i >= 0; i--) {
            if (A[i] > last) {
                return -1;
            }
            last = A[i];
        }
        int pre = max;
        for (int i = index + 1; i < len; i++) {
            if (A[i] > pre) {
                return -1;
            }
            pre = A[i];
        }
        return index;
    }

}
