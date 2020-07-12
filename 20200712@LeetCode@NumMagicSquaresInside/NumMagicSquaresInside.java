package com.dl.textK;

public class NumMagicSquaresInside {

    public int numMagicSquaresInside(int[][] grid) {
        int row = grid.length;
        if (row == 0) {
            return 0;
        }
        int col = grid[0].length;
        int count = 0;
        for (int i = 0; i <= row - 3; i++) {
            for (int j = 0; j <= col - 3; j++) {
                System.out.println(grid[i + 1][j + 1]);
                if (grid[i + 1][j + 1] != 5 
                    || grid[i][j] * grid[i][j + 1] * grid[i][j + 2]
                    *  grid[i + 1][j] * grid[i + 1][j + 1] * grid[i + 1][j + 2]
                    *  grid[i + 2][j] * grid[i + 2][j + 1] * grid[i + 2][j + 2] != 362880) {
                        continue;
                }
                // 第一行
                if (grid[i][j] + grid[i][j + 1] + grid[i][j + 2] != 15) {
                    continue;
                }
                // 第二行
                if (grid[i + 1][j] + grid[i + 1][j + 1] + grid[i + 1][j + 2] != 15) {
                    continue;
                } 
                // 第三行
                if (grid[i + 2][j] + grid[i + 2][j + 1] + grid[i + 2][j + 2] != 15) {
                    continue;
                }
                // 第一列
                if (grid[i][j] + grid[i + 1][j] + grid[i + 2][j] != 15) {
                    continue;
                } 
                // 第二列
                if (grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 2][j + 1] != 15) {
                    continue;
                }
                // 第三列
                if (grid[i][j + 2] + grid[i + 1][j + 2] + grid[i + 2][j + 2] != 15) {
                    continue;
                }  
                // 左上到右下对角线
                if (grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2] != 15) {
                    continue;
                }                 
                // 右上到左下对角线
                if (grid[i + 2][j] + grid[i + 1][j + 1] + grid[i][j + 2] != 15) {
                    continue;
                } 
                count++;   
            }
        }
        return count;
    }
	
}
