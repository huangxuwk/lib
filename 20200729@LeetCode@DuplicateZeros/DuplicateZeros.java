package src.com.jd.leetcode.textG;

public class DuplicateZeros {

    public void duplicateZeros(int[] arr) {
        int len = arr.length;
        if (len == 0) {
            return;
        }
        for (int i = 0; i < len; i++) {
            if (arr[i] == 0) {
                for (int j = len - 1; j > i + 1; j--) {
                    arr[j] = arr[j - 1];
                }
                if (i + 1 < len) {
                    arr[++i] = 0;
                }
            }
        }
    }

}
