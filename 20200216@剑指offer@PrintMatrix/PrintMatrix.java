package com.dl.test1;

import java.util.ArrayList;

public class PrintMatrix {

	public PrintMatrix() {
	}
	
    public ArrayList<Integer> printMatrix(int [][] matrix) {
    	if (matrix == null || matrix.length == 0) {
    		return null;
    	}
    	ArrayList<Integer> result = new ArrayList<>();
    	add(result, matrix);
    	while ((matrix = revolve(matrix)) != null) {
    		add(result, matrix);
    	}
		return result;
    }
    
    private void add(ArrayList<Integer> result, int[][] matrix) {
    	int col = matrix[0].length;
		for (int j = 0; j < col; j++) {
			result.add(matrix[0][j]);
		}   	
    }
    
    private int[][] revolve(int[][] matrix) {
    	int row = matrix.length - 1;
    	if (row == 0) {
    		return null;
    	}
    	int col = matrix[0].length;
    	int[][] result = new int[col][row];    
    	for (int i = 1; i < row+1; i++) {
    		for (int j = col - 1; j >= 0; j--) {
    			result[col - j - 1][i-1] = matrix[i][j];
    		}
    	}
    	
		return result;
    }
    
    public static void main(String[] args) {
    	PrintMatrix p = new PrintMatrix();
    	int[][] matrix = {};
//    	for (int i = 0; i < 4; i++) {
//    		for (int j = 0; j < 4; j++) {
//    			System.out.println(matrix[i][j]);
//    		}
//    	}
    	ArrayList<Integer> result  = p.printMatrix(matrix);
    	for (Integer integer : result) {
			System.out.println(integer);
		}
	}
}
