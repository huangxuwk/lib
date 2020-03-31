package com.dl.test4;

public class MajorityElement {

    public int majorityElement(int[] nums) {
        int result = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (result == nums[i]) {
                count++;
            } else if (--count == 0) {
                result = nums[i];
                count = 1;
            }
        }
        return result;
    }
}
