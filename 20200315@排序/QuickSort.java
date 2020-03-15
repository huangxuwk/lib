package com.dl.sort;

/**
 * 快速排序<br>
 * 时间复杂度：<br>
 * 		平均：O(nlogn)<br>
 * 		最差：冒泡排序，O(n^2)<br>
 * 非稳定排序<br>
 * @author dl
 *
 */
public class QuickSort {

	public QuickSort() {
	}
	
	public void quickSort(int[] array) {
		int length;
		if (array == null || (length = array.length) <= 1) {
			return;
		}
		
		findIndex(array, 0, length - 1);
	}
	
	private void findIndex(int[] array, int start, int end) {
		if (start >= end) {
			return;
		}
		int temp = array[start];
		int head = start;
		int tail = end;
		
		/*
		 * 先以第一个数为基准数，从后先找小于基准数的，填充下标0
		 * 再从前往后找，填充刚才后面的下标。
		 */
		while (head < tail) {
			while (head < tail && array[tail] > temp) {
				tail--;
			}
			if (head < tail) {
				array[head++] = array[tail];
			}
			while (head < tail && array[head] < temp) {
				head++;
			}
			if (head < tail) {
				array[tail--] = array[head];
			}
		}
		array[head] = temp;
		findIndex(array, start, head - 1);
		findIndex(array, head + 1, end);
	}
}
