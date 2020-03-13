package com.dl.test5;

public class MovingCount {

    public int movingCount(int threshold, int rows, int cols){
        if (threshold < 0 || rows < 0 || cols < 0) {
            return 0;
        }
        boolean[][] flag = new boolean[rows][cols];
        return moving(threshold, rows, cols, flag, 0, 0);
    }
    
    private int moving(int threshold, int rows, int cols, boolean[][] flag, int x, int y) {
        if (x >= rows || y >= cols || x < 0 || y < 0 || flag[x][y] == true) {
            return 0;
        }
        int sum = 0;
        int tx = x;
        int ty = y;
        while (tx > 0) {
            sum += tx % 10;
            tx /= 10;
        }
        while (ty > 0) {
            sum += ty % 10;
            ty /= 10;
        }
        if (sum <= threshold) {
            flag[x][y] = true;
            return moving(threshold, rows, cols, flag, x, y - 1)
                + moving(threshold, rows, cols, flag, x, y + 1)
                + moving(threshold, rows, cols, flag, x - 1, y)
                + moving(threshold, rows, cols, flag, x + 1, y)
                + 1;
        }
        
        return 0;
    }
	
}
