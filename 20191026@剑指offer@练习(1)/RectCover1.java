package com.dl.test2;

/*
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，
 * 总共有多少种方法？
 */
public class RectCover1 {
	private static boolean sign = false;
	
    public int RectCover(int target) {
    	if (sign == false) {
    		if (target == 0) {
    			return 0;
    		}
    	}
    	sign = true;
    	if (target == 0) {
    		return 1;
    	} else if (target < 0) {
    		return 0;
    	}
    	int result = 0;
    	result += RectCover(target - 1);
    	result += RectCover(target - 2);
    	
		return result;
    }
    
    public static void main(String[] args) {
		System.out.println(new RectCover1().RectCover(0));
	}
}
