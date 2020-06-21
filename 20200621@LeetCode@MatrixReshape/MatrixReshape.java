package com.dl.text4;

public class MatrixReshape {

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums.length == 0) {
            return nums;
        }
        int row = nums.length;
        int col = nums[0].length;
        if (row * col < r * c) {
            return nums;
        }
        int ri = 0;
        int cj = 0;
        int[][] re = new int[r][c];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (cj >= c)  {
                    cj = 0;
                    ri++;
                }                
                re[ri][cj++] = nums[i][j];
            }
        }
        return re;
    }
	
}
