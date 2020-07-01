package com.dl.text8;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FindShortestSubArray {

    public int findShortestSubArray(int[] nums) {
        int len = nums.length;
        if (len <= 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int n : nums) {
            Integer v = map.get(n);
            if (v == null) {
                map.put(n, 1);
                max = max < 1 ? 1 : max;
            } else {
                map.put(n, v + 1);
                max = Math.max(max, v + 1);
            }
        }
        Iterator<Integer> it = map.keySet().iterator();
        int minLen = Integer.MAX_VALUE;
        while (it.hasNext()) {
            int k = it.next();
            if (map.get(k) == max) {
                minLen = Math.min(minLen, getLast(k, nums) - getFirst(k, nums) + 1);
            }
        }
        return minLen;
    }

    private int getFirst(int n, int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (n == nums[i]) {
                return i;
            }
        }
        return -1;
    }

    private int getLast(int n, int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            if (n == nums[i]) {
                return i;
            }
        }
        return -1;
    }
	
}
