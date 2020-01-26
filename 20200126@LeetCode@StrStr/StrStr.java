package com.dl.test2;

public class StrStr {

	public StrStr() {
	}
	
    public int strStr(String haystack, String needle) {
        int aLen = haystack.length();
        int bLen = needle.length();
        // 如果被找的字符串为null或者空字符串，返回0
        if(needle == null || bLen == 0){
            return 0;
        }
        // 如果被找出的字符串长度大于给定的字符串长度，直接返回-1
        if(bLen > aLen){
            return -1;
        }

        for(int i = 0;i <= aLen - bLen; i++){
            if(needle.equals(haystack.substring(i, i + bLen))){
                return i;
            }
        }
        return -1;        
    }
}
