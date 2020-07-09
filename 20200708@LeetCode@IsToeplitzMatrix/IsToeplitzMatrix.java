package com.dl.textJ;

public class IsToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix.length == 0) {
            return true;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == -1) {
                    continue;
                }
                int pre = matrix[i][j];
                int k = i;
                int l = j;
                matrix[k++][l++] = -1;
                while (k < row && l < col) {
                    if (matrix[k][l] != pre) {
                        return false;
                    }
                    matrix[k++][l++] = -1;
                }
            }
        }
        return true;
    }
	
}
