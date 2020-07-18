package src.com.jd.leetcode.textD;

public class SortArrayByParityII {

    public int[] sortArrayByParityII(int[] A) {
        int len = A.length;
        if (len == 0) {
            return A;
        }
        int j = 1;
        for (int i = 0; i < len; i += 2) {
            if ((A[i] & 1) == 1) {
                while ((A[j] & 1) == 1) {
                    j += 2;
                }
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        return A;
    }

}
