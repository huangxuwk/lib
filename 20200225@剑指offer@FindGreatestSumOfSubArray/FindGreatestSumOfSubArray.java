package com.dl.test2;

public class FindGreatestSumOfSubArray {
	
	public FindGreatestSumOfSubArray() {
	}
	
    public int findGreatestSumOfSubArray(int[] array) {
    	int length;
    	if (array == null || (length = array.length) == 0) {
    		return 0;
    	}
    	if (length == 1) {
    		return array[0];
    	}
    	int max = 0;
    	boolean ok = true;
    	int temp = 0;
    	for (int index = 0; index < length; index++) {
    		temp += array[index];
    		if (ok) {
    			max = temp;
    			ok =false;
    		}
    		if (temp > max) {
    			max = temp;
    		}
    		if (temp < 0) {
    			temp = 0;
    		}
    	}
    	
		return max;
    }
    
    public static void main(String[] args) {
		int[] array = new int[] {-2,-8,-1,-5,-9};
		System.out.println(new FindGreatestSumOfSubArray().findGreatestSumOfSubArray(array));
	}
}
