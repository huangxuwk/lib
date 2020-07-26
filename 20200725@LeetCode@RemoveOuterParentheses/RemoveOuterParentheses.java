package com.dl.textL;

public class RemoveOuterParentheses {

    public String removeOuterParentheses(String S) {
        if (S == null || S == "") {
            return "";
        }
        int count = 0;
        char[] inputs = S.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < inputs.length; i++) {
            char currentChar = inputs[i];
            if (currentChar == '(') {
                if (count > 0) {
                    sb.append(currentChar);
                }
                count++;
            } else {
                count--;
                if (count > 0) {
                    sb.append(currentChar);
                }
            }
        }
        return sb.toString();
    }
	
}
