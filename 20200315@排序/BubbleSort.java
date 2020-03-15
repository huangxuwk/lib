package com.dl.sort;

/**
 * ð������<br>
 * ʱ�临�Ӷȣ�O{(n - 1) * (n / 2)} -> O(n^2)��<br>
 * �ȶ����򣬳�����ͬ����ʱ�����˳�򲻱䣻<br>
 * ��ԭ�����������ģ���ôֻ���ñȽ�һ�飬Ч�ʷǳ��ߣ�
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
