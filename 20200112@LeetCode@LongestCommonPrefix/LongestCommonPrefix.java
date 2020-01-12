package com.dl.test2;

public class LongestCommonPrefix {
	
	public LongestCommonPrefix() {
	}
	
    public String longestCommonPrefix(String[] strs) {
    	int length = strs.length;
    	if (length <= 0) {
    		return "";
    	}
    	
    	StringBuffer result = new StringBuffer();
    	char curChar;
    	int index = 0;
    	
    	while (true) {
    		if (index >= strs[0].length()) {
    			break;
    		}
    		curChar = strs[0].charAt(index);
    		int i;
        	for (i = 0; i < length; i++) {
    			if (index >= strs[i].length() || strs[i].charAt(index) != curChar) {
    				break;
    			}
    		}
        	if (i != strs.length) {
        		break;
        	}
        	result.append(curChar);
        	index++;
    	}
    	
    	return result.toString();
    }
    
    static class Test {
    	public static void main(String[] args) {
    		System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"a"}));
    	}
    }
}
