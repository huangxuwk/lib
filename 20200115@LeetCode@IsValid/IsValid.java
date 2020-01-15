package com.dl.test2;

public class IsValid {

	public IsValid() {
	}
	
    public boolean isValid(String s) {
        while(s.contains("()") || s.contains("[]") || s.contains("{}")) {
            s = s.replaceAll("\\(\\)","");
            s = s.replaceAll("\\[\\]","");
            s = s.replaceAll("\\{\\}","");
        }
        return s.length() == 0;
    }
    
    static class test {
    	public static void main(String[] args) {
			System.out.println(new IsValid().isValid("()[]{}"));
		}
    }
}
