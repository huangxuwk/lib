package com.dl.test3;

public class Add_Solution {

	public Add_Solution() {
	}
	
    public int Add(int num1,int num2) {
        while (num2 > 0) {
            // ^ �൱����ӣ�����������߽�λ
            int temp = (num1 ^ num2);
            // ���λ
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
