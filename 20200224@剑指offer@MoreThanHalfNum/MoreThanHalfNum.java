package com.dl.test2;

import java.util.HashMap;

public class MoreThanHalfNum {

	public MoreThanHalfNum() {
	}
	
    public int MoreThanHalfNum_Solution(int [] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        int halfLen = length / 2;
        if (length < 1) {
        	return 0;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        Integer tar;
        for (Integer integer : array) {
            tar = map.get(integer);
            if (tar == null) {
                tar = 1; 
            } else {
                tar++;
            }
            if (tar > halfLen) {
                return integer;   
            }            
            map.put(integer, tar);
        }
        return 0;
    }
}
