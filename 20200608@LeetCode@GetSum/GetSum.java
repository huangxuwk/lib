package com.dl.test10;

public class GetSum {

    public int getSum(int a, int b) {
        while (b != 0) {
            // 无进位相加
            int tmp = a ^ b;
            // 计算进位，如果不为0，下一次循环进行无进位相加
            b = (a & b) << 1;
            a = tmp;
        }
        return a;
    }
	
}
