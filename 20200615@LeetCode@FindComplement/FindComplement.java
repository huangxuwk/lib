package com.dl.text1;

public class FindComplement {

    public int findComplement(int num) {
        int temp = num;
        int re = 0;
        // ��num��������Чλ��Ӧ��re��Ϊ1��Ȼ���������
        // 5 ��101 ^ 111 = 010;
        while (num > 0) {
            re = (re << 1) + 1;;
            num >>= 1;
        }
        return re ^ temp;
    }
	
}
