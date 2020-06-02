package com.dl.test7;

import java.util.HashMap;
import java.util.Map;

public class IsIsomorphic {

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        for (int i = 0; i < ss.length; i++) {
            Character v = map.get(ss[i]);
            if (v == null) {
                if (map.containsValue(ts[i])) {
                    return false;
                }
                map.put(ss[i], ts[i]);
            } else {
                if (v != ts[i]) {
                    return false;
                }
            }
        }
        return true;
    }
	
}
