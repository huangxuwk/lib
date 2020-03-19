package com.dl.test3;

public class PlusOne {

	public PlusOne() {
	}
	
    public int[] plusOne(int[] digits) {
        int length;
        if (digits == null || (length = digits.length) == 0) {
            return digits;
        }

        int sum;
        int temp = 1;
        for (int index = length - 1; index >= 0; index--) {
        	Double.valueOf(temp);
            sum = digits[index] + temp;
            digits[index] = sum % 10;
            temp = sum / 10;
            if (temp == 0) {
                return digits;
            }
        }
        int[] result = new int[length + 1];
        result[0] = temp;
        
        return result;
    }
}
