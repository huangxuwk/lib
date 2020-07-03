package com.dl.textI;

public class PivotIndex {

    public int pivotIndex(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        int ln = 0;
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        for (int i = 0; i < len; i++) {
            if ((sum - nums[i]) % 2 == 0 && (sum - nums[i]) / 2 == ln) {
                return i;
            }
            ln += nums[i];
        }
        return -1;
    }
	
}
