package com.dl.text5;

public class MaxCount {

    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) {
            return m * n;
        }
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (int[] o : ops) {
            if (o[0] < minX) {
                minX = o[0];
            }
            if (o[1] < minY) {
                minY = o[1];
            }
        }
        return minX * minY;
    }
	
}
