package com.dl.test;

public class ReplaceSpace {
	public String replaceSpace(StringBuffer str) {
        if(str==null){
            return null;
        }
       StringBuilder newStr = new StringBuilder();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '){
                newStr.append('%');
                newStr.append('2');
                newStr.append('0');
            }else{
                newStr.append(str.charAt(i));
            }
        }
        return newStr.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(new ReplaceSpace().replaceSpace(new StringBuffer("hello world")));
	}
}
