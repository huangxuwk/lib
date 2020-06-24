package com.dl.text6;

public class JudgeSquareSum {
	
    public boolean judgeSquareSum(int c) {  
        int left = 0;
        int right = (int)Math.sqrt(c);
        while (left <= right) {
            int re = left * left + right * right;
            if (re == c) {
                return true;
            } else if (re < c) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
	
}