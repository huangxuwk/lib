package com.dl.test4;

import java.util.HashMap;

public class Duplicate {

	public Duplicate() {
	}
	
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (numbers == null || length < 0 || duplication == null) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (map.put(numbers[i], 1) != null) {
                duplication[0] = numbers[i];
                return true;
            }
        }
        return false;
    }
}
