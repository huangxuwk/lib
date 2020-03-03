package com.dl.test3;

public class LastRemaining {

	public LastRemaining() {
	}
	
    public int LastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 0) {
            return -1;
        }
        int[] peos = new int[n];
        int count = n;
        int num = 0;
        int index = 0;
        for (;count > 1; index = (index + 1) % n) {
            if (peos[index] == 1) {
                continue;
            }
            if (++num == m) {
                peos[index] = 1;
                num = 0;
                count--;
                System.out.println(index);
            }
        }
        
        return (index + 1) % n;
    }
    
    public static void main(String[] args) {
		new LastRemaining().LastRemaining_Solution(5, 3);
	}
}
