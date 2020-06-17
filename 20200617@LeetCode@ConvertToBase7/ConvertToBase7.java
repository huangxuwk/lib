package com.dl.text2;

public class ConvertToBase7 {

    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        if (num < 0) {
            flag = false;
        }
        num = Math.abs(num);
        while (num > 0) {
            sb.append(num % 7);
            num /= 7;
        }
        if (!flag) {
            sb.append('-');
        }
        return sb.reverse().toString();
    }
	
}
