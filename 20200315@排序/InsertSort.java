package com.dl.sort;

/**
 * 插入排序<br>
 * 时间复杂度：O{(n - 1) * (x + y)} [ x + y = n ]  ->  O(n^2)<br>
 * 稳定排序，出现相同的数时，相对顺序不变；
 * @author dl
 *
 */
public class InsertSort {
	
	public InsertSort() {
	}
	
	/*
	 * 3, 5, 2, 4, 7, 1, 6, 8, 8
	 * (3) (5, 2, 4, 7, 1, 6, 8, 8)
	 * (3, 5) (2, 4, 7, 1, 6, 8, 8)
	 * (2, 3, 5) (4, 7, 1, 6, 8, 8)
	 */
	public void insertSort(int[] array) {
		int length;
		if (array == null || (length = array.length) <= 1) {
			return;
		}
		
		int temp;
		int i;
		int j;
		int t;
		for (i = 1; i < length; i++) {
			temp = array[i];
			for (j = 0; j < i && temp > array[j]; j++) {
			}
			for (t = i; t > j; t--) {
				array[t] = array[t-1];
			}
			array[j] = temp;
		}
	}
}
