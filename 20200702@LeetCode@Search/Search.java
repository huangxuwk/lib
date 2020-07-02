package com.dl.textH;

public class Search {

    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len <= 0) {
            return -1;
        }
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int index = left + (right - left) / 2;
            if (nums[index] == target) {
                return index;
            } else if (nums[index] > target) {
                right = index - 1;
            } else {
                left = index + 1;
            }
        }
        return -1;
    }
	
}
