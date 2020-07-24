package src.com.jd.leetcode.textF;

public class BitwiseComplement {

    public int bitwiseComplement(int N) {
        if (N == 0) {
            return 1;
        }
        int i = 0;
        int sum = 0;
        while (N > 0) {
            if ((N & 1) == 0) {
                sum += 1 << i;
            }
            i++;
            N >>= 1;
        }
        return sum;
    }

}
