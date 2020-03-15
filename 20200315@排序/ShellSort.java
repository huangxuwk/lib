package com.dl.sort;

/**
 * 希尔排序-进阶版插入排序<br>
 * 时间复杂度:<br>
 * 增量序列即为步长序列<br>
 * 1、希尔增量：最坏 O(n^2)  -- 2^k<br>
 * 2、Hibbard增量：最坏 O(n^1.5)  -- 2^(k-1)<br>
 * 3、Sedgewick增量：最坏 O(n^1.3)<br>
 * 不稳定排序，出现相同的数时，相对顺序会变；
 * 
 * @author dl
 *
 */
public class ShellSort {

	public ShellSort() {
	}
	
	public void shellSort(int[] array) {
		int length;
		if (array == null || (length = array.length) <= 1) {
			return;
		}
		for (int step = length / 2; step > 0; step /= 2) {
			for (int index = 0; index < step; index++) {
				stepInsertSort(array, index, step);
			}
		}
	}
	
	private void stepInsertSort(int[] array, int start, int step) {
		int length;
		if (array == null || (length = array.length) <= 1) {
			return;
		}
		int temp;
		int i;
		int j;
		int t;
		for (i = start + step; i < length; i += step) {
			temp = array[i];
			for (j = start; j < i && temp > array[j]; j += step) {
			}
			for (t = i; t > j; t -= step) {
				array[t] = array[t-step];
			}
			array[j] = temp;
		}
	}
}
