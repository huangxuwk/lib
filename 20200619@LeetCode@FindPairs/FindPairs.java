package com.dl.text3;

import java.util.HashSet;
import java.util.Set;

public class FindPairs {

    public int findPairs(int[] nums, int k) {
        if (nums.length == 0 || k < 0) {
            return 0;
        }
        Set<Integer> nSet = new HashSet<>();
        Set<String> rSet = new HashSet<>();
        for (int n : nums) {
            // ��n�����
            int t1 = n + k;
            if (nSet.contains(t1)) {
                rSet.add(n + "" + t1);
            }
            // ��nС����
            int t2 = n - k;
            if (nSet.contains(t2)) {
                rSet.add(t2 + "" + n);
            }
            nSet.add(n);
        }
        return rSet.size();
    }
	
}
