package com.dl.test5;

public class CutRope {

    public int cutRope(int target) {
        if (target < 1) {
            return 0;
        }
        int max = -1;
        for (int count = target; count > 1; count--) {
            int re = result(target, count);
            if (re > max) {
                max = re;
            }
        }
        return max;
    }
    
    private int result(int target, int count) {
        int sum = 1;
        double c = Double.valueOf(count);
        int len;
        if ((target / c - target / count) >= 0.5) {
            len = target / count + 1;
        } else {
            len = target / count;
        }
        while (target > 0) {
            sum *= len;
            target -= len;
            if (target - len <= 0) {
                sum *= target;
                target = 0;               
            }
        }
        return sum;
    }
    
    public static void main(String[] args) {
		System.out.println(new CutRope().cutRope(3));
	}
}
