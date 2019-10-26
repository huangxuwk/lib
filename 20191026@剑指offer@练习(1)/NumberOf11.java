package com.dl.test2;

/*
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class NumberOf11 {

    public int NumberOf1(int n) {
    	int flag = 1;
    	int count = 0;
    	
    	if (n <= -1) {
    		n = -n;
    	}
    	
    	while (n != 0) {
    		if ((n & flag) == 1) {
    			count++;
    		}
    		// 补码右移，会在最高位补1，不是补0
    		// >>> : 无符号右移，逻辑右移
    		n = n >>> 1;
    	}
    	
		return count;
    }
    
    public static void main(String[] args) {
		System.out.println(new NumberOf11().NumberOf1(-7));
	}
}
