package com.dl.test8;

public class MissingNumber {

    public int missingNumber(int[] nums) {
        long orgSum = nums.length * (nums.length + 1) / 2;
        for (int num : nums) {
            orgSum -= num;
        }
        return (int)orgSum;
    }
	
}
