package com.dl.test;

/*
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
/*
 * 1 : 1
 * 2 : 2
 * 3 : 3 
 * 4 : 1111 211 121 112 22   5
 * 5 : 11111 2111 1211 1121 1112 221 212 122 
 */
public class JumpFloor {
    public int JumpFloor1(int target) {
    	if (target == 0) {
    		return 1;
    	}
    	if (target == -1) {
    		return 0;
    	}
    	int result = 0;
    	result += JumpFloor1(target - 1);
    	result += JumpFloor1(target - 2);
    	
		return result;
    }
    public static void main(String[] args) {
		System.out.println(new JumpFloor().JumpFloor1(4));
	}
}
