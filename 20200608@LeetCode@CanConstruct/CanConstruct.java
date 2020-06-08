package com.dl.test10;

public class CanConstruct {

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() == 0) {
            return true;
        }
        // ������ģ���ϣ������Ͱ����
        int[] count = new int[26];
        char[] rs = ransomNote.toCharArray();
        char[] ms = magazine.toCharArray();
        for (char c : ms) {
            count[c - 'a']++;
        }
        for (char c : rs) {
            if (--count[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
	
}
