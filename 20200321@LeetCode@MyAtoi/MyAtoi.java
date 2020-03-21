package com.dl.test3;

public class MyAtoi {

    public int myAtoi(String str) {
        if (str == null || str.length() == 0 || (str = str.trim()).length() == 0) {
            return 0;
        }
        char[] array = str.toCharArray();

        int sign = 1;
        int result = 0;
        int num = 1;
        int index = 1;
        if (array[0] == '-') {
            sign = -1;
        } else if (array[0] == '+') {
        } else if ((num = array[0] - 48) <= 9 && num >= 0) {
            result += num;
        } else {
            return 0;
        }    
        while (index < array.length && ((num = array[index] - 48) <= 9 && num >= 0)) {
            num *= sign;
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && num > 7)) {
                return Integer.MAX_VALUE;
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && num < -8)) {
                return Integer.MIN_VALUE;
            }
            result = result * 10 + num;
            index++;
        }

        return (int)result;
    }
    
    public static void main(String[] args) {
    	System.out.println(Long.MAX_VALUE);
		System.out.println(new MyAtoi().myAtoi(
				"  -42"));
	}

}
