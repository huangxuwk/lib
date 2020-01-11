package com.dl.test1;

public class Palindrome {
	
	public Palindrome() {
	}
	
    public boolean isPalindrome(int x) {
    	if (x < 0 || x % 10 == 0) {
    		return false;
    	}
    	
    	if (x == reverse(x)) {
    		return true;
    	}
    	
		return false;
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
		System.out.println(new Palindrome().isPalindrome(10));
	}
}
