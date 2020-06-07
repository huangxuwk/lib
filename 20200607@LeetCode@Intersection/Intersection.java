package com.dl.test9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Intersection {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {            
            if (set1.contains(num)) {
                set2.add(num);
            }
        }
        int[] re = new int[set2.size()];
        Iterator<Integer> iterator = set2.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            re[index++] = iterator.next();
        }
        return re;
    }
	
}
