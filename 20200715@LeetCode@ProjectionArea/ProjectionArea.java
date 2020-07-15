package src.com.jd.leetcode.textB;

public class ProjectionArea {

    public int projectionArea(int[][] grid) {
        int row = grid.length;
        if (row == 0) {
            return 0;
        }
        int col = grid[0].length;
        // 俯视图，所有有值的和
        int re = 0;
        // 侧视图，每一行求最大值
        for (int i = 0; i < row; i++) {
            int max = 0;
            for (int j = 0; j < col; j++) {
                if (grid[i][j] > 0) {
                    re++;
                }
                if (grid[i][j] > max) {
                    max = grid[i][j];
                }
            }
            re += max;
            max = 0;
        }
        // 主视图，每一列求最大值
        for (int j = 0; j < col; j++) {
            int max = 0;
            for (int i = 0; i < col; i++) {
                if (grid[i][j] > max) {
                    max = grid[i][j];
                }
            }
            re += max;
            max = 0;
        }
        return re;
    }

}
