package src.com.jd.leetcode.textE;

public class SumEvenAfterQueries {

    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int sum = 0;
        for (int a : A) {
            if ((a & 1) == 0) {
                sum += a;
            }
        }
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int val = queries[i][0];
            int index = queries[i][1];
            if ((A[index] & 1) == 0) {
                sum -= A[index];
            }
            A[index] += val;
            if ((A[index] & 1) == 0) {
                sum += A[index];
            }
            answer[i] = sum;
        }
        return answer;
    }

}
