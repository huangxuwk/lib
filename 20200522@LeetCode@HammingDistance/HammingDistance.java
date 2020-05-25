package com.dl.test5;

public class HammingDistance {

    public int hammingDistance(int x, int y) {
        int count = 0;
        for (int i = 0; i < 31; i++) {
            int tx = x >> i;
            int ty = y >> i;
            if ((tx & 1) != (ty & 1)) {
                count++;
            }
        }
        return count;
    }
}
