package com.dl.test3;

public class Add_Solution {

	public Add_Solution() {
	}
	
    public int Add(int num1,int num2) {
        while (num2 > 0) {
            // ^ 相当于相加，但舍弃了最高进位
            int temp = (num1 ^ num2);
            // 求进位
            num2 = (num1 & num2) << 1;
            num1 = temp;
//            System.out.println(num1);
//            System.out.println(num2);
        }
        return num1;
    }
    
    public static void main(String[] args) {
    	new Add_Solution().Add(-1, 2);
	}
}
