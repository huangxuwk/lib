package com.dl.test2;

public class SearchInsert {

	public SearchInsert() {
	}
	
    public int searchInsert(int[] nums, int target) {
        for(int i = 0; i < nums.length;i++){
            if(nums[i] >= target){
                return i;
            }
        }
        return nums.length;        
    }
}
