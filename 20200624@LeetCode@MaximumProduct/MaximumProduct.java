package com.dl.text6;

import java.util.Arrays;

public class MaximumProduct {

    public int maximumProduct(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len <= 3) {
            int re = 1;
            for (int n : nums) {
                re *= n;
            }
            return re;
        }
        Arrays.sort(nums);
        return Math.max(nums[len - 1] * nums[len - 2] * nums[len - 3],
            nums[len - 1] * nums[0] * nums[1]);
    }
	
}
