package com.dl.text5;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FindLHS {

    public int findLHS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            Integer v = map.get(n);
            if (v == null) {
                map.put(n, 1);
            } else {
                map.put(n, v + 1);
            }
        }
        Iterator<Integer> iterator = map.keySet().iterator();
        int maxLen = 0;
        while (iterator.hasNext()) {
            int k = iterator.next();
            int k1 = k + 1;
            int k2 = k - 1;
            int v1 = 0;
            int v2 = 0;
            if (map.containsKey(k1)) {
                v1 = map.get(k1);
            } 
            if (map.containsKey(k2)) {
                v2 = map.get(k2);
            }
            if (v1 == 0 && v2 == 0) {
                continue;
            }
            int v = v1 >= v2 ? v1 : v2;
            if (v + map.get(k) > maxLen) {
                maxLen = v + map.get(k);
            }
        }
        return maxLen;
    }
	
}
