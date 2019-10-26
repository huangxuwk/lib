package com.dl.test2;

import java.util.ArrayList;
import java.util.List;

/*
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class reOrderArray1 {

    public void reOrderArray(int [] array) {
    	int length = array.length;
        if (length <= 0) {
        	return;
        }
        int oddCount = 0;
        int evenCount = 0;
        for (int i = 0; i < length; i++) {
        	if (array[i] % 2 == 0) {
        		evenCount++;
        	} else {
        		oddCount++;
        	}
        }
        List<Integer> oddList = new ArrayList<Integer>();
        List<Integer> evenList = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
        	if (array[i] % 2 == 0) {
        		evenList.add(array[i]);
        	} else {
        		oddList.add(array[i]);
        	}
        }
        for (int i = 0; i < oddCount; i++) {
        	array[i] = oddList.get(i);
        }
        for (int i = 0; i < evenCount; i++) {
        	array[i + oddCount] = evenList.get(i);
        }
    }
    
    public static void main(String[] args) {
    	new reOrderArray1().reOrderArray(new int[] {0, 2, 1, 4, -1, 3});
    }
}
