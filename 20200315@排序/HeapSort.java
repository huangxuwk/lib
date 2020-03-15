package com.dl.sort;

/**
 * ������<br>
 * ʱ�临�Ӷȣ�O(n) + O(nlogn) = O(nlogn)<br>
 * ���ȶ�����
 * @author dl
 *
 */
public class HeapSort {

	public HeapSort() {
	}
	
	public void heapSort(int[] array) {
		int length;
		if (array == null || (length = array.length) == 0 || length == 1) {
			return;
		}
		// ��������ѣ����ڵ�û�е���
		for (int root = length / 2 - 1; root > 0; root--) {
			adjustHeap(array, length, root);
		}
		int temp;
		// ÿ�ε������ڵ�󣬸��ڵ���ĩ�ڵ㽻���������µ������ڵ�
		while (length > 0) {
			adjustHeap(array, length, 0);
			
			temp = array[0];
			array[0] = array[length - 1];
			array[length - 1] = temp;
			
			length--;
		}
	}
	
	private void adjustHeap(int[] array, int length, int root) {
		int leftIndex;
		int rightIndex;
		int maxIndex;
		int temp;
		// ֻ�õ�����Ҷ�ӽ�㣬���һ����Ҷ�ӽ��Ϊ length / 2 - 1
		while (root < length / 2) {
			leftIndex = (root << 1) + 1;
			rightIndex = (root << 1) + 2;
			if (rightIndex < length) {
				maxIndex = array[leftIndex] > array[rightIndex] 
						? leftIndex 
						: rightIndex;
			} else {
				maxIndex = leftIndex;
			}
			if (array[root] < array[maxIndex]) {
				temp = array[root];
				array[root] = array[maxIndex];
				array[maxIndex] = temp;
				
				root = maxIndex;
			} else {
				break;
			}
		}
	}
}
