package com.dl.test3;

public class Sum_Solution {

	public Sum_Solution() {
	}
	
    public int sum_Solution(int n) {
        int sum = n;
        @SuppressWarnings("unused")
		boolean ok = (n > 0) && (sum += sum_Solution(n - 1)) > 0;
        return sum;
    }
}
