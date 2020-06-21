package com.dl.text4;

import java.util.Arrays;

public class FindUnsortedSubarray {

    public int findUnsortedSubarray(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int[] temp = nums.clone();
        Arrays.sort(nums);
        int len = nums.length;
        int left = len - 1;
        int right = 0;
        for (int i = 0; i < len; i++) {
            if (temp[i] != nums[i]) {
                left = i;
                break;
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            if (temp[i] != nums[i]) {
                right = i;
                break;
            }
        }
        System.out.println(left + " -- " + right);
        return right - left + 1 > 0 ? right - left + 1 : 0;
    }
	
}
