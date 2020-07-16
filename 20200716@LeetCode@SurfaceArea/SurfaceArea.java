package src.com.jd.leetcode.textC;

public class SurfaceArea {

    // 将所有面积累加，再减去左和上重叠的部分
    public int surfaceArea(int[][] grid) {
        int row  = grid.length;
        if (row == 0) {
            return 0;
        }
        int col = grid[0].length;
        int sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                sum += 4 * grid[i][j] + 2;
                if (i - 1 >= 0) {
                    sum -= 2 * Math.min(grid[i][j], grid[i - 1][j]);
                }
                // 6 + 10
                if (j - 1 >= 0) {
                    sum -= 2 * Math.min(grid[i][j], grid[i][j - 1]);
                }
            }
        }
        return sum;
    }

}
