package src.com.jd.leetcode.textD;

public class MinDeletionSize {

    public int minDeletionSize(String[] A) {
        int len = A.length;
        if (len == 0) {
            return 0;
        }
        int re = 0;
        int colLen = A[0].length();
        for (int j = 0; j < colLen; j++) {
            char pre = A[0].charAt(j);
            for (int i = 1; i < len; i++) {
                if (A[i].charAt(j) - pre < 0) {
                    re++;
                    break;
                }
                pre = A[i].charAt(j);
            }
        }
        return re;
    }

}
