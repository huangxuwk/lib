package com.dl.test10;

public class GetSum {

    public int getSum(int a, int b) {
        while (b != 0) {
            // �޽�λ���
            int tmp = a ^ b;
            // �����λ�������Ϊ0����һ��ѭ�������޽�λ���
            b = (a & b) << 1;
            a = tmp;
        }
        return a;
    }
	
}
