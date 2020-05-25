package com.dl.test5;

public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        int length = nums.length;
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                if (index++ != i) {
                    nums[i] = 0;
                }
            }
        }
    }
}
