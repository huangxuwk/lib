package com.dl.test;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        int indexArray[] = new int[2];
        
        for (int i = 0; i < nums.length; i++) {
        	numMap.put(nums[i], i);
		}
        int tmp;
        Integer index;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
        	tmp = target - nums[i];
        	index = numMap.get(tmp);
        	if (index != null && index != i) {
        		indexArray[j] = i;
        		indexArray[j + 1] = index;
        		break;
        	}
		}
        
		return indexArray;
    }
    
    static class Test {
    	public static void main(String[] args) {
			int array[] = {2, 7, 7, 15};
			
			for (int i : new TwoSum().twoSum(array, 14)) {
				System.out.println(i);
			}
		}
    }
}
