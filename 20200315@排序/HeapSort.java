package com.dl.sort;

/**
 * 堆排序<br>
 * 时间复杂度：O(n) + O(nlogn) = O(nlogn)<br>
 * 非稳定排序；
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
		// 创建大根堆，根节点没有调整
		for (int root = length / 2 - 1; root > 0; root--) {
			adjustHeap(array, length, root);
		}
		int temp;
		// 每次调整根节点后，根节点与末节点交换，再重新调整根节点
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
		// 只用调整非叶子结点，最后一个非叶子结点为 length / 2 - 1
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
