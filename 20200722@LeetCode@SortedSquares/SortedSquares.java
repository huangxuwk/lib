package src.com.jd.leetcode.textE;

import java.util.Arrays;

public class SortedSquares {

    public int[] sortedSquares(int[] A) {
        int len = A.length;
        if (len == 0) {
            return new int[]{};
        }
        int[] re = new int[len];
        for (int i = 0; i < len; i++) {
            if (A[i] < 0) {
                A[i] = -1 * A[i];
            }
        }
        Arrays.sort(A);
        int i = 0;
        for (int a : A) {
            re[i++] = a * a;
        }
        return re;
    }

}
