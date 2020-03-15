package com.dl.sort;

/**
 * ѡ������<br>
 * ʱ�临�Ӷȣ�O{(n - 1) * (n / 2)} -> O(n^2)<br>
 * ���ȶ����򣬳�����ͬ����ʱ�����˳���䣻
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
