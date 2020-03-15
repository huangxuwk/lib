package com.dl.sort;

public class Test {
	
	public static void main(String[] args) {
		int[] array = new int[] {3, 5, 9, 4, 7, 1, 6, 8, 8};
//		int[] array = new int[] {1, 2, 3, 4, 5};
//		new InsertSort().insertSort(array);
//		new SelectionSort().selectedSort(array);
//		new BubbleSort().bubbleSort(array);
//		new ShellSort().shellSort(array);
//		new HeapSort().heapSort(array);
		new QuickSort().quickSort(array);
		for (int i : array) {
			System.out.println(i);
		}
	}
}
