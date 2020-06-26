package com.dl.text6;

import java.util.ArrayList;
import java.util.List;

public class FindErrorNums {

    public int[] findErrorNums(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return new int[]{};
        }
        int[] sign = new int[len + 1];
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (sign[num] == 1) {
                list.add(num);
            } else {
                sign[num] = 1;
            }
        }
        for (int i = 1; i < sign.length; i++) {
            if (sign[i] == 0) {
                list.add(i);
            }
        }
        int index = 0;
        int[] re = new int[list.size()];
        for (int n : list) {
            re[index++] = n;
        }
        return re;
    }
	
}
