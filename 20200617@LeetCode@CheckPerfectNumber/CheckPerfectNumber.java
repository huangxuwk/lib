package com.dl.text2;

public class CheckPerfectNumber {

    public boolean checkPerfectNumber(int num) {
        if (num <= 1) {
            return false;
        }
        // ��������������Ҫ��1��������
        int sum = 1;
        for (int i = (int)Math.sqrt(num); i > 1; i--) {
            if (num % i == 0) {
                sum = sum + i + num / i;
            }
        } 
        return sum == num;
    }
	
}
