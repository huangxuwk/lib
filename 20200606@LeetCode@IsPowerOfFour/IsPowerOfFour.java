package com.dl.test9;

public class IsPowerOfFour {

    public boolean isPowerOfFour(int num) {
        if (num < 1) {
            return false;
        }
        // �����4���ݴη�����һ����2���ݴη����������4���ݣ���Ψһ��1Ӧ���ڶ����Ƶ�����λ��
        // ��˿�����1010 1010 ������ ���������㣬�����ͬ����4���ݣ������ͬ��Ϊ0������4����
        if (((num & (num - 1)) == 0) && ((num & 0x55555555) == num)) {
            return true;
        }
        return false;
    }
    
}
