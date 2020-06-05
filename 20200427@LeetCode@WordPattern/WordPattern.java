package com.dl.test9;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        if (pattern.length() == 0 && str.length() == 0) {
            return true;
        }
        if (pattern.length() == 0 || str.length() == 0) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        char[] pa = pattern.toCharArray();
        String[] ss = str.split(" ");
        if (pa.length != ss.length) {
            return false;
        }
        for (int i = 0; i < pa.length; i++) {
            String v = map.get(pa[i]);
            if (v == null) {
                if (set.contains(ss[i])) {
                    return false;
                }
                map.put(pa[i], ss[i]);
                set.add(ss[i]);
            } else {
                if (!v.equals(ss[i])) {
                    return false;
                }
            }
        }
        return true;
    }
    
	
}
