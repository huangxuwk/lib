package com.dl.sort;

/**
 * 选择排序<br>
 * 时间复杂度：O{(n - 1) * (n / 2)} -> O(n^2)<br>
 * 不稳定排序，出现相同的数时，相对顺序会变；
 * @author dl
 *
 */
public class SelectionSort {
	
	public SelectionSort() {
	}
	
	/*
	 * 3, 5, 2, 4, 7, 1, 6, 8, 8
	 * (1) (3, 5, 2, 4, 7, 6, 8, 8)
	 * (1, 2) (3, 5, 4, 7, 6, 8, 8)
	 * (1, 2, 3) (5, 4, 7, 6, 8, 8)
	 */
	public void selectedSort(int[] array) {
		int length;
		if (array == null || (length = array.length) <= 1) {
			return;
		}
		
		int minIndex;
		int temp;
		int i;
		int j;
		for (i = 0; i < length - 1; i++) {
			for (minIndex = j = i; j < length; j++) {
				if (array[minIndex] > array[j]) {
					minIndex = j;
				}
			}
			if (minIndex != i) {
				temp = array[minIndex];
				array[minIndex] = array[i];
				array[i] = temp;
			}
		}
	}
}
