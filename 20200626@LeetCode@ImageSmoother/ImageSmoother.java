package com.dl.text6;

public class ImageSmoother {
    public int[][] imageSmoother(int[][] M) {
        if (M.length == 0) {
            return new int[][]{};
        }
        int row = M.length;
        int col = M[0].length;
        int[][] re = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                re[i][j] = smoother(M, row, col, i, j);
            }
        }
        return re;
    }

    private int smoother(int[][] M, int row, int col, int i, int j) {
        int sum = M[i][j];
        int count = 1;
        if (i - 1 >= 0) {
            // 左上
            if (j - 1 >= 0) {
                sum += M[i - 1][j - 1];
                count++;
            }
            // 上
            sum += M[i - 1][j];
            count++;
            // 右上
            if (j + 1 < col) {
                sum += M[i - 1][j + 1];
                count++;
            }
        }
        if (i + 1 < row) {
            // 左下
            if (j - 1 >= 0) {
                sum += M[i + 1][j - 1];
                count++;
            }
            // 下
            sum += M[i + 1][j];
            count++;
            // 右下
            if (j + 1 < col) {
                sum += M[i + 1][j + 1];
                count++;
            }            
        }
        // 左
        if (j - 1 >= 0) {
            sum += M[i][j - 1];
            count++;
        } 
        // 右
        if (j + 1 < col) {
            sum += M[i][j + 1];
            count++;
        } 
        return sum / count;
    }
}
