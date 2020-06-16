package com.dl.text2;

public class FindMaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int preZ = -1;
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                len = Math.max(len, i - preZ - 1);
                preZ = i;
            }
        }
        return Math.max(len, nums.length - preZ - 1);
    }
	
}
