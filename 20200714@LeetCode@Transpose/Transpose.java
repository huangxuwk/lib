package src.com.jd.leetcode.textB;

public class Transpose {

    public int[][] transpose(int[][] A) {
        // 1 4  1 2 3
        // 2 5  4 5 6
        // 3 6
        int row = A.length;
        if (row == 0) {
            return A;
        }
        int col = A[0].length;
        int[][] re = new int[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                re[j][i] = A[i][j];
            }
        }
        return re;
    }

}
