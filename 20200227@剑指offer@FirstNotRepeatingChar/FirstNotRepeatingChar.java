package com.dl.test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FirstNotRepeatingChar {

	public FirstNotRepeatingChar() {
	}
	
    public int firstNotRepeatingChar(String str) {
        if (str == null || str.equals("")){
            return -1;
        }
        char[] chars = str.toCharArray();
        int length = chars.length;
        HashMap<Character, Integer> indexMap = new HashMap<>();
        ArrayList<Character> chList = new ArrayList<>();
        Integer temp;
        char ch;
        for (int index = 0; index < length; index++) {
            ch = chars[index];
            temp = indexMap.get(ch);
            if (temp == null && !chList.contains(ch)) {
                indexMap.put(ch, index);
            } else {
            	indexMap.remove(ch);
                chList.add(ch);
            }
        }
        ArrayList<Integer> list = new ArrayList<>(indexMap.values());
        Collections.sort(list);
        
        return list.get(0);
    }
    
    public static void main(String[] args) {
		System.out.println(new FirstNotRepeatingChar().firstNotRepeatingChar("googgle"));
	}
}
