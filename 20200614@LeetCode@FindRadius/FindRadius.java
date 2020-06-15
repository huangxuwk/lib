package com.dl.text1;

import java.util.Arrays;

public class FindRadius {

    public int findRadius(int[] houses, int[] heaters) {
        if (houses.length == 0 || heaters.length == 0) {
            return 0;
        }
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int re = 0;
        for (int i = 0; i < houses.length; i++) {
            System.out.println(houses[i] + " --- " + getMin(houses[i], heaters));
            re = Math.max(re, Math.abs(getMin(houses[i], heaters) - houses[i]));
        }
        return re;
    }

    private int getMin(int n, int[] heaters) {
        int left = 0;
        int right = heaters.length - 1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (heaters[mid] < n) {
                if (mid == heaters.length - 1 || heaters[mid + 1] > n) {
                    min = mid;
                }
                left = mid + 1;
            } else {
                if (mid == 0 || heaters[mid - 1] < n) {
                    max = mid;
                }
                right = mid - 1;
            }
        }
        if (min != Integer.MAX_VALUE && max == Integer.MAX_VALUE) {
            return heaters[min];
        }
        if (min == Integer.MAX_VALUE && max != Integer.MAX_VALUE) {
            return heaters[max];
        }
        return n - heaters[min] > heaters[max] - n ? heaters[max] : heaters[min];
    }
	
}
