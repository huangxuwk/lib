package com.dl.test3;

import java.util.ArrayList;

public class FindNumbersWithSum {

	public FindNumbersWithSum() {
	}
	
	public ArrayList<Integer> findNumbersWithSum(int [] array,int sum) {
		int length;
        ArrayList<Integer> result = new ArrayList<>();
        if (array == null || (length = array.length) < 2 
            || array[0] > sum) {
            return result;
        }
        int detla;
        for (int index = 0; index < length; index++) {
            detla = sum - array[index];
            if (binarySearch(array, length, detla)) {
                result.add(array[index]);
                result.add(detla);
                return result;
            }
        }
        
        return result;
    }
    
    private boolean binarySearch(int[] array, int length, int num) {
        int start = 0;
        int end = length - 1;
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (array[mid] > num) {
                end = mid - 1;
            } else if (array[mid] < num) {
                start = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
