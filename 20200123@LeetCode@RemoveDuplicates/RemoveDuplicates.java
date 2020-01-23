package com.dl.test2;

public class RemoveDuplicates {

	public RemoveDuplicates() {
	}
	
    public int removeDuplicates(int[] nums) {
        // Ê¹ÓÃË«Ö¸Õë
		if(nums==null || nums.length == 1){
		    return nums.length;
		}
		int i = 0,j =1;
		while(j<nums.length){
		    if(nums[i]==nums[j]){
		        j++;
		    }else{
		        i++;
		        nums[i]=nums[j];
		        j++;
		    }
		}
		return i+1;
}
}
