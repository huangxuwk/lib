package com.dl.test;

/*
��Ҷ�֪��쳲��������У�����Ҫ������һ������n���������쳲��������еĵ�n���0��ʼ����0��Ϊ0����
n<=39
 */
public class FibonacciSequence {
    public int Fibonacci(int n) {
    	if (n == 0) {
    		return 0;
    	} else if (n == 1) {
    		return 1;
    	}
    	n = Fibonacci(n - 1) + Fibonacci(n - 2);
		return n;
    }
    
    public static void main(String[] args) {
		System.out.println(new FibonacciSequence().Fibonacci(8));
	}
}
