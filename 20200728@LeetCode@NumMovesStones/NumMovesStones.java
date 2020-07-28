package src.com.jd.leetcode.textG;

import java.util.Arrays;

public class NumMovesStones {

    public int[] numMovesStones(int a, int b, int c) {
        int[] ar = new int[3];
        ar[0] = a;
        ar[1] = b;
        ar[2] = c;
        Arrays.sort(ar);
        int[] re = new int[2];
        if (ar[1] - ar[0] == 1 && ar[2] - ar[1] == 1) {
            re[0] = 0;
        } else if (ar[1] - ar[0] == 1 || ar[2] - ar[1] == 1
                || ar[1] - ar[0] == 2 || ar[2] - ar[1] == 2) {
            re[0] = 1;
        } else {
            re[0] = 2;
        }
        re[1] = ar[2] - ar[0] - 2;
        return re;
    }

}
