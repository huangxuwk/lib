package com.dl.test4;

public class IsNumeric {
	
	public IsNumeric() {
	}
	
    public boolean isNumeric(char[] str) {
        try {
            Double.valueOf(new String(str));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
