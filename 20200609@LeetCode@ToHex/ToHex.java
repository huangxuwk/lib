package com.dl.test10;

public class ToHex {

    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        // �����ִ�С�������±��Ӧ
        char[] ca = "0123456789abcdef".toCharArray();
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            // ���ÿ��Ǹ������ڼ�������Ѿ����ò������ˣ�����ֱ��ת��
            // ÿ4λ2����λ��Ӧһλ16����λ
            sb.append(ca[num & 0x0f]);
            num >>>= 4;
        }
        return sb.reverse().toString();
    }
	
}
