package com.dl.text7;

public class FindLengthOfLCIS {

    public int findLengthOfLCIS(int[] nums) {
        int len = nums.length;
        if (len <= 0) {
            return 0;
        }
        int maxLen = 0;
        int count = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                count++;
            } else {
                if (count > maxLen) {
                    maxLen = count;
                }
                count = 1;
            }
        }
        return Math.max(maxLen, count);
    }
	
}
