package com.dl.test9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Intersect {

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            Integer v = map.get(num);
            if (v == null) {
                map.put(num, 1);
            } else {
                map.put(num, v + 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            Integer v = map.get(num);
            if (v != null && v > 0) {
                list.add(num);
                map.put(num, v - 1);
            }
        }
        int[] re = new int[list.size()];
        int index = 0;
        for (int num : list) {
            re[index++] = num;
        }
        return re;
    }
	
}
