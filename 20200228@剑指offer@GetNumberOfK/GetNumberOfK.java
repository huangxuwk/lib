package com.dl.test2;

public class GetNumberOfK {

	public GetNumberOfK() {
	}
	
    public int getNumberOfK(int [] array , int k) {
        int length;
        if (array == null || (length = array.length) == 0) {
            return 0;
        }
        int first = getFirstIndex(array, k, 0, length - 1);
        int last = getLastIndex(array, k, 0, length - 1);
        if (first != - 1&& last != -1) {
        	return last - first + 1;
        }
        
        return 0;
    }
    
    private int getFirstIndex(int[] array, int k, int start, int end) {
        int mid;
        while (end >= start) {
            mid = (start + end) >> 1;
            if (array[mid] > k) {
                end = mid - 1;
            } else if (array[mid] < k) {
                start = mid + 1;
            } else if (mid - 1 >= 0 && array[mid - 1] == k && array[mid] == k) {
            	end = mid - 1;
            } else if  (mid - 1 < 0 || (array[mid - 1] < k && array[mid] == k)) {
            	return mid;
            }
        }
    	return -1;
    }
    
    private int getLastIndex(int[] array, int k, int start, int end) {
    	int length = array.length;
        int mid;
        while (end >= start) {
            mid = (start + end) >> 1;
            if (array[mid] > k) {
                end = mid - 1;
            } else if (array[mid] < k) {
                start = mid + 1;
            } else if (mid + 1 < length && array[mid + 1] == k && array[mid] == k) {
            	start = mid + 1;
            } else if  (mid + 1 >= length || (array[mid + 1] > k && array[mid] == k)) {
            	return mid;
            }
        }
    	return -1;
    }
    
    public static void main(String[] args) {
    	int[] array = new int[] {1,2,2,2,3};
		System.out.println(new GetNumberOfK().getNumberOfK(array, 4));
	}

}
