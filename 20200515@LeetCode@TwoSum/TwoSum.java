package com.dl.test6;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        if (numbers.length == 0) {
            return null;
        }
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }
        for (int i = 0; i < numbers.length; i++) {
            Integer temp = map.get(target - numbers[i]);
            if (temp != null) {
                result[0] = i + 1;
                result[1] = temp + 1;
                return result;
            }
        }
        return null;
    }
	
}
