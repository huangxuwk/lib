package com.dl.sort;

/**
 * ϣ������-���װ��������<br>
 * ʱ�临�Ӷ�:<br>
 * �������м�Ϊ��������<br>
 * 1��ϣ��������� O(n^2)  -- 2^k<br>
 * 2��Hibbard������� O(n^1.5)  -- 2^(k-1)<br>
 * 3��Sedgewick������� O(n^1.3)<br>
 * ���ȶ����򣬳�����ͬ����ʱ�����˳���䣻
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
