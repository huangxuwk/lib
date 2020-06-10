package com.dl.test10;

public class ToHex {

    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        // 将数字大小与数组下标对应
        char[] ca = "0123456789abcdef".toCharArray();
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            // 不用考虑负数，在计算机中已经是用补码存放了，所以直接转换
            // 每4位2进制位对应一位16进制位
            sb.append(ca[num & 0x0f]);
            num >>>= 4;
        }
        return sb.reverse().toString();
    }
	
}
