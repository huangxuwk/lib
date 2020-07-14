package src.com.jd.leetcode.textB;

public class BinaryGap {

    public int binaryGap(int N) {
        int maxLen = 0;
        int preIn = -1;
        int index = 0;
        while (N > 0) {
            if ((N & 1) == 1) {
                if (preIn == -1) {
                    preIn = index;
                } else {
                    if (maxLen < index - preIn) {
                        maxLen = index - preIn;
                    }
                    preIn = index;
                }
            }
            N >>= 1;
            index++;
        }
        return maxLen;
    }

}
