package src.com.jd.leetcode.textG;

public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        int[] sign = new int[1001];
        for (int s : stones) {
            sign[s]++;
        }
        for (int i = 1000; i > 0; i--) {
            if (sign[i] == 0) {
                continue;
            }
            if (sign[i] % 2 != 0) {
                int j = i - 1;
                while (j > 0 && sign[j] == 0) {
                    j--;
                }
                if (j == 0) {
                    return i;
                }
                sign[i - j]++;
                sign[j]--;
            }
            sign[i] = 0;
        }
        return 0;
    }

}
