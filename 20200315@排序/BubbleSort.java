package com.dl.sort;

/**
 * 冒泡排序<br>
 * 时间复杂度：O{(n - 1) * (n / 2)} -> O(n^2)；<br>
 * 稳定排序，出现相同的数时，相对顺序不变；<br>
 * 若原数组就是有序的，那么只需用比较一遍，效率非常高；
 * @author dl
 *
 */
public class BubbleSort {
	
	public BubbleSort() {
	}
	
	public void bubbleSort(int[] array) {
		int length;
		if (array == null || (length = array.length) <= 1) {
			return;
		}
		
		int i;
		int j;
		int temp;
		boolean isBubble;
		completed:
		for (i = 0; i < length - 1; i++) {
			isBubble = false;
			for (j = 0; j < length - i - 1; j++) {
				if (array[j] > array[j+1]) {
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					isBubble = true;
				}
			}
			if (!isBubble) {
				break completed;
			}
		}
	}
}
