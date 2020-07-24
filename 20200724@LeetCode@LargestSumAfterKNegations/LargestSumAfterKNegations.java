package src.com.jd.leetcode.textF;

import java.util.Arrays;

public class LargestSumAfterKNegations {

    public int largestSumAfterKNegations(int[] A, int K) {
        int len = A.length;
        if (len == 0) {
            return 0;
        }
        Arrays.sort(A);
        for (int i = 0; i < len && A[i] < 0 && K > 0; i++, K--) {
            A[i] = -1 * A[i];
        }
        int sum = 0;
        if (K == 0 || K % 2 == 0) {
            for (int a : A) {
                sum += a;
            }
        } else {
            Arrays.sort(A);
            for (int a : A) {
                sum += a;
            }
            sum -= 2 * A[0];
        }
        return sum;
    }

}
