package com.dl.text1;

import java.util.TreeSet;

public class ThirdMax {

    public int thirdMax(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int n : nums) {
            set.add(n);
            if (set.size() > 3) {
                set.remove(set.first());
            }
        }
        return set.size() == 3 ? set.first() : set.last();
    }
	
}
