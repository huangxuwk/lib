package com.dl.textI;

public class DominantIndex {

    public int dominantIndex(int[] nums) {
        int max = Integer.MIN_VALUE;
        int lessMax = Integer.MIN_VALUE - 1;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                lessMax = max;
                max = nums[i];
                index = i;
            } else if (nums[i] < max && nums[i] > lessMax) {
                lessMax = nums[i];
            }
        }
        System.out.println(max);
        System.out.println(2 * lessMax);
        if (max >= 2 * lessMax) {
            return index;
        }
        return -1;
    }
	
}
