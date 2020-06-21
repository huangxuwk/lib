package com.dl.text4;

import java.util.HashSet;
import java.util.Set;

public class DistributeCandies {

    public int distributeCandies(int[] candies) {
        Set<Integer> set = new HashSet<>();
        for (int num : candies) {
            set.add(num);
        }
        return Math.min(set.size(), candies.length / 2);
    }
	
}
