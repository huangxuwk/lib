package src.com.jd.leetcode.textC;

public class SmallestRangeI {

    public int smallestRangeI(int[] arr, int k) {
        int maxNum = Integer.MIN_VALUE;
        int minNum = Integer.MAX_VALUE;
        for (int i : arr) {
            maxNum = Math.max(maxNum, i);
            minNum = Math.min(minNum, i);
        }
        return maxNum - minNum > k * 2 ? maxNum - minNum - k * 2 : 0;
    }

}
