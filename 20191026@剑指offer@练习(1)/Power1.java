package com.dl.test2;

/*
 * 给定一个double类型的浮点数base和int类型的整数exponent。
 * 求base的exponent次方。
 * 保证base和exponent不同时为0
 */
public class Power1 {

    public double Power(double base, int exponent) {
    	if (base == 0) {
    		return 0;
    	}
    	if (exponent == 0) {
    		return 1;
    	}
    	double result = 1.0;
    	if (exponent > 0) {
        	for (int i = 0; i < exponent; i++) {
        		result *= base;
        	}	
    	} else {
        	for (int i = 0; i < (-exponent); i++) {
        		result *= 1 / base;
        	}	
    	}
    	
		return result;
    }
    
    public static void main(String[] args) {
		System.out.println(new Power1().Power(2, -3));
	}
}
