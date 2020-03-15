package com.dl.sort;

/**
 * ��������<br>
 * ʱ�临�Ӷȣ�<br>
 * 		ƽ����O(nlogn)<br>
 * 		��ð������O(n^2)<br>
 * ���ȶ�����<br>
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
		 * ���Ե�һ����Ϊ��׼�����Ӻ�����С�ڻ�׼���ģ�����±�0
		 * �ٴ�ǰ�����ң����ղź�����±ꡣ
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
