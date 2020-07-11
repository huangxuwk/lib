package com.dl.textJ;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MostCommonWord {

    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph.length() == 0) {
            return "";
        }
        paragraph = paragraph.replace('!', ' ');
        paragraph = paragraph.replace('?', ' ');
        paragraph = paragraph.replace('\'', ' ');
        paragraph = paragraph.replace(',', ' ');
        paragraph = paragraph.replace(';', ' ');
        paragraph = paragraph.replace('.', ' ');
        paragraph = paragraph.toLowerCase();
        String[] sa = paragraph.split(" ");
        Set<String> set = new HashSet<>();
        for (String s : banned) {
            set.add(s);
        }
        Map<String, Integer> map = new HashMap<>();
        for (String s : sa) {
            if (set.contains(s) || s.equals("")) {
                continue;
            }
            Integer v = map.get(s);
            if (v == null) {
                map.put(s, 1);
            } else {
                map.put(s, v + 1);
            }
        }
        int max = 0;
        String re = "";
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String k = it.next();
            int v = map.get(k);
            if (v > max) {
                max = v;
                re = k;
            }
        }
        return re;
    }
	
}
