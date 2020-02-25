package com.dl.test2;

public class NumberOf1Between1AndN_Solution {

	public NumberOf1Between1AndN_Solution() {
	}
	
    public int numberOf1Between1AndN_Solution(int n) {
    	if (n < 1) {
    		return 0;
    	}
    	if (n == 1) {
    		return 1;
    	}
    	int result = 0;
    	int temp;
    	for (int index = 1; index <= n; index++) {
    		temp = index;
    		while (temp > 0) {
    			if (temp % 10 == 1) {
    				result++;
    			}
    			temp /= 10;
    		}
    	}
    	
		return result;
    }
    
    public static void main(String[] args) {
		System.out.println(new NumberOf1Between1AndN_Solution().numberOf1Between1AndN_Solution(13));
	}
}
