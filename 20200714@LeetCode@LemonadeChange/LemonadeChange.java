package src.com.jd.leetcode.textB;

public class LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        if (bills.length == 0) {
            return true;
        }
        // 5元
        int fc = 0;
        // 10元
        int tc = 0;
        // // 20元
        // int twc = 0;
        for (int b : bills) {
            if (b == 5) {
                fc++;
                continue;
            }
            if (b == 10) {
                if (fc > 0) {
                    fc--;
                    tc++;
                    continue;
                } else {
                    return false;
                }
            }
            if (b == 20) {
                if (tc > 0 && fc > 0) {
                    tc--;
                    fc--;
                } else {
                    if (fc >= 3) {
                        fc -= 3;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
