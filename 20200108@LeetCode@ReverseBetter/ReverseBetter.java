package com.dl.test1;

public class ReverseBetter {
	
	public ReverseBetter() {
	}
	
	public int reverse(int x) {
		long rst=0;
        while(x!=0){
            rst= rst*10+ x%10;
            x=x/10;
        }
        
        if((int)rst!=rst){
            return 0;
        }else{
            return (int)rst;
        }     
	}
	
	public static void main(String[] args) {
		System.out.println(new Reverse().reverse(-2147483648));
	}
}
