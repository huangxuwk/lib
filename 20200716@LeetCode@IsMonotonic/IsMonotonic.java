package src.com.jd.leetcode.textC;

public class IsMonotonic {

    public boolean isMonotonic(int[] A) {
        int len = A.length;
        if (len == 0 || len == 1) {
            return true;
        }
        boolean up = A[len - 1] > A[0] ? true : false;
        if (up) {
            int pre = -100001;
            for (int a : A) {
                if (a < pre) {
                    return false;
                }
                pre = a;
            }
        } else {
            int pre = 100001;
            for (int a : A) {
                if (a > pre) {
                    return false;
                }
                pre = a;
            }
        }
        return true;
    }

}
