package com.dl.test1;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt {

	public RomanToInt() {
	}
	
    public int romanToInt(String s) {
    	// map效率低
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        int res = 0;
        for(int i = 0; i < s.length(); i ++) {
            int curVal = map.get(s.charAt(i));
            int nextVal = i < s.length() - 1 ? map.get(s.charAt(i + 1)) : 0;
            /*
             * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
             * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
             * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
             */
            // 这里可能会出现问题，只能期待传入的数字没有问题
            // 如果是 XD 就有问题，值是490
            res += curVal < nextVal ? - curVal : curVal;
        }
        
        return res;
    }
}
