package com.dl.test5;

import java.util.ArrayList;

public class MaxInWindows {

	public MaxInWindows() {
	}
	
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (num == null || size < 1) {
            return list;
        }
        int start = 0;
        int end = start + size - 1;
        for (; start >=0 && end < num.length; start++, end++) {
            list.add(getMaximum(num, start, end));
        }
        return list;
    }
    
    private int getMaximum(int[] num, int start, int end) {
        int max = num[start];
        for (int index = start + 1; index <= end; index++) {
            if (num[index] > max) {
                max = num[index];
            }
        }
        return max;
    }
}
