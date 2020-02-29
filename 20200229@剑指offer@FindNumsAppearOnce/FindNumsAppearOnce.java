package com.dl.test3;

import java.util.HashSet;
import java.util.Iterator;

public class FindNumsAppearOnce {
	
	public FindNumsAppearOnce() {
	}
	
    public void findNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if (array == null || num1 == null 
            || num2 == null || array.length < 2) {
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int num : array) {
            if (!set.remove(num)) {
                set.add(num);
            }
        }
        Iterator<Integer> iterator = set.iterator();
        num1[0] = iterator.next();
        num2[0] = iterator.next();
    }
}
