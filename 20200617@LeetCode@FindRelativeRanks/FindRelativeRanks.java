package com.dl.text2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindRelativeRanks {

    public String[] findRelativeRanks(int[] nums) {
        String[] res = new String[nums.length];
        //先确定每个运动员一开始站立的位置i
        Map<Integer, Integer> map=new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        //对成绩进行排序
        Arrays.sort(nums);
        int j=nums.length-1;
        for (int i = 0; i < res.length; i++) {
            if (j>=0) {
                if (j==nums.length-1) {
                    res[map.get(nums[j])]="Gold Medal";
                }else if (j==nums.length-2) {
                    res[map.get(nums[j])]="Silver Medal";
                }else if (j==nums.length-3) {
                    res[map.get(nums[j])]="Bronze Medal";
                }else {
                res[map.get(nums[j])]=i+1+"";
                }
                j--;
            }			
        }	
        return res;         
    }
	
}
