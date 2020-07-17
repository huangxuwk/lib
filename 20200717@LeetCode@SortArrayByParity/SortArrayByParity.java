package src.com.jd.leetcode.textC;

public class SortArrayByParity {

    public int[] sortArrayByParity(int[] A) {
        int len = A.length;
        if (len == 0) {
            return A;
        }
        int left = 0;
        int right = len - 1;
        while (left < right) {
            while (left < right && A[left] % 2 ==0) {
                left++;
            }
            while (left < right && A[right] % 2 == 1) {
                right--;
            }
            int temp = A[left];
            A[left] = A[right];
            A[right] = temp;
        }
        return A;
    }

}
