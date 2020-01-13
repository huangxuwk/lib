package com.dl.test2;

public class LongestCommonPrefixBetter {
	
	public LongestCommonPrefixBetter() {
	}
	
    public String longestCommonPrefix(String[] strs) {
    	int length = strs.length;
    	if (length <= 0) {
    		return "";
    	}
    	String result = strs[0];
    	for (String string : strs) {
			while (!string.startsWith(result)) {
				// ����Ϊ1����һ�ο϶�Ϊ�մ�
				if (result.length() == 1) {
					return "";
				}
				// ���ַ�����ǰ�и�һλ
				result = result.substring(0, result.length() - 1);
			}
		}
    	
    	return result;
    }
    
    static class Test {
    	public static void main(String[] args) {
    		System.out.println(new LongestCommonPrefixBetter().longestCommonPrefix(new String[]{"a"}));
    	}
    }
}
