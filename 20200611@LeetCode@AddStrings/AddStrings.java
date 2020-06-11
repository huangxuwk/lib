package com.dl.text1;

public class AddStrings {

    public String addStrings(String num1, String num2) {
        if (num1.length() == 0) {
            return num2;
        }
        if (num2.length() == 0) {
            return num1;
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int carry = 0;
        int sum = 0;
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < len1 && i < len2; i++) {
            int n1 = Integer.valueOf(num1.charAt(i) - '0');
            int n2 = Integer.valueOf(num2.charAt(i) - '0');
            sum = (n1 + n2 + carry) % 10;
            carry = (n1 + n2 + carry) / 10;
            sb.append(sum);
        }
        if (i < len1) {
        	while (carry > 0 && i < len1) {
                int n1 = Integer.valueOf(num1.charAt(i++) - '0');
                sum = (n1 + carry) % 10;
                carry = (n1 + carry) / 10;
                sb.append(sum);     		
        	}
            if (i < len1) {
        	    sb.append(num1.substring(i));
            }
        }
        if (i < len2) {
        	while (carry > 0 && i < len2) {
                int n2 = Integer.valueOf(num2.charAt(i++) - '0');
                sum = (n2 + carry) % 10;
                carry = (n2 + carry) / 10;
                sb.append(sum);     		
        	}
            if (i < len2) {
        	    sb.append(num2.substring(i));
            }
        }
        if (carry > 0) {
        	sb.append(carry);
        }
        return sb.reverse().toString();
    }
	
    public static void main(String[] args) {
		System.out.println(new AddStrings().addStrings("9", "99"));
	}
    
}
