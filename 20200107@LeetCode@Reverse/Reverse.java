package com.dl.test;

public class Reverse {
	
	public Reverse() {
	}
 
	public int reverse(int x) {
		int[] bit = new int[32];
		int i = 0;
		int tmp = Math.abs(x);
		
		while (tmp != 0) {
			bit[i++] = tmp % 10;
			tmp /= 10;
		}
		int j = 0;
		while (j < 32 && bit[j] == 0) {
			j++;
		}
		long sum = 0;
		int target = 0;
		for (int index = j; index < i; index++) {
			target = (int) (i - index - 1 == 0 ? bit[index] : Math.pow(10, i - index - 1) * bit[index]);
			sum +=  target;
		}
		
		if (sum > Integer.MAX_VALUE  || sum < 0) {
			return 0;
		}
		return (int) (sum = x > 0 ? sum : (sum /= -1));
    }
	
	static class test {
		public static void main(String[] args) {
			System.out.println(new Reverse().reverse(-2147483648));
		}
	}
}
