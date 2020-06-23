package com.dl.text5;

public class CanPlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n <= 0) {
            return true;
        }
        int len = flowerbed.length;
        if (len == 0 || (len == 1 && flowerbed[0] == 1)) {
            return false;
        }
        if ((len == 1 && flowerbed[0] == 0) && n == 1) {
            return true;
        }
        int count = 0;
        if (flowerbed[0] == 0 && (len == 1 || flowerbed[1] == 0)) {
            count++;
        }
        int pre = flowerbed[1];
        for (int i = 2; i < len - 2; i+=2) {
            int next = flowerbed[i + 1];
            if (pre == 0 && next == 0 && flowerbed[i] == 0) {
                if (++count >= n) {
                    return true;
                }
            }
            pre = next;
        }
        if (len > 2 && flowerbed[len - 1] == 0 && flowerbed[len - 2] == 0) {
            count++;
        }
        return count >= n;
    }
	
}
