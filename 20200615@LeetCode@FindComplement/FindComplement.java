package com.dl.text1;

public class FindComplement {

    public int findComplement(int num) {
        int temp = num;
        int re = 0;
        // 将num的所有有效位对应的re置为1，然后异或运算
        // 5 ：101 ^ 111 = 010;
        while (num > 0) {
            re = (re << 1) + 1;;
            num >>= 1;
        }
        return re ^ temp;
    }
	
}
