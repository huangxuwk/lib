package src.com.jd.leetcode.textE;

import java.util.Arrays;

public class LargestPerimeter {

    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i > 1; i--) {
            int a = A[i];
            int b = A[i - 1];
            int c = A[i - 2];
            if (a < b + c) {
                return a + b + c;
            }
        }
        return 0;
    }

}
