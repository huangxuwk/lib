package com.dl.test2;

public class StrStr {

	public StrStr() {
	}
	
    public int strStr(String haystack, String needle) {
        int aLen = haystack.length();
        int bLen = needle.length();
        // ������ҵ��ַ���Ϊnull���߿��ַ���������0
        if(needle == null || bLen == 0){
            return 0;
        }
        // ������ҳ����ַ������ȴ��ڸ������ַ������ȣ�ֱ�ӷ���-1
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
