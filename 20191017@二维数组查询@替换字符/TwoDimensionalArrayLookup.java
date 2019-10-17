package com.dl.test;

public class TwoDimensionalArrayLookup {
	
	public boolean Find(int target, int [][] array) {
		// 列数
		int rowLength = array[0].length;
		// 行数
		int colLength = array.length;
		
		for (int i = 0; i < colLength; i++) {
			for (int j = 0; j < rowLength; j++) {
				if (array[i][j] == target) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[][] array = {{1, 2, 3}, {4, 5, 6}};
		System.out.println(new TwoDimensionalArrayLookup().Find(2, array));
	}
}
