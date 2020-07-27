package src.com.jd.leetcode.textF;

import java.util.Arrays;
import java.util.Comparator;

public class AllCellsDistOrder {

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][2];
        for (int i = 0, k = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                res[k][0] = i;
                res[k++][1] = j;
            }
        }

        Arrays.sort(res, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                int da = Math.abs(a[0] - r0) + Math.abs(a[1] - c0);
                int db = Math.abs(b[0] - r0) + Math.abs(b[1] - c0);
                return da - db;
            }
        });

        return res;
    }

}
