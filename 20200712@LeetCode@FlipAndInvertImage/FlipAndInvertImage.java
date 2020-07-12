package com.dl.textK;

public class FlipAndInvertImage {

    public int[][] flipAndInvertImage(int[][] A) {
        int row = A.length;
        if (row == 0) {
            return A;
        }
        int col = A[0].length;
        int[][] re = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = col - 1; j >= 0; j--) {
                re[i][col - j - 1] = A[i][j] == 1 ? 0 : 1;
            }
        }
        return re;
    }
	
}
