package com.dl.test4;

import java.util.HashMap;

public class StrToInt {

	public StrToInt() {
	}
	
    public int strToInt(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        
        map.put('+', -1);
        map.put('-', -2);
        for (int i = 0; i < 10; i++) {
            map.put(("" + i).toCharArray()[0], i);
        }
        char[] array = str.toCharArray();
        int length = array.length;
        Integer value;
        long sum = 0;
        for (int i = length - 1; i >= 0; i--) {
            value = map.get(array[i]);
            if (value == null) {
                return 0;
            }
            if (value >= 0 && value <= 9) {
                sum += value * Math.pow(10, length - 1 - i);
            } else if (value == -1) {
                if (i > 0) {
                    return 0;
                }
            } else if (value == -2) {
                if (i > 0) {
                    return 0;
                } else {
                    sum *= -1;
                }
            }
        }
        if (sum < Integer.MIN_VALUE || sum > Integer.MAX_VALUE) {
            return 0;
        }
        return (int)sum;
    }
}
