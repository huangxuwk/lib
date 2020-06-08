package com.dl.test10;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class FirstUniqChar {

    public int firstUniqChar(String s) {
        if (s.length() == 0) {
            return -1;
        }
        Map<Character, Integer> map = new LinkedHashMap<>();
        char[] ss = s.toCharArray();
        for (int i = 0; i < ss.length; i++) {
            Integer v = map.get(ss[i]);
            if (v != null) {
                map.put(ss[i], -1);
            } else {
                map.put(ss[i], i);
            }
        }
        if (map.size() == 0) {
            return -1;
        }
        Iterator<Integer> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            int v = iterator.next();
            if (v != -1) {
                return v;
            }
        }
        return -1;
    }
	
}
