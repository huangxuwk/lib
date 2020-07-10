package com.jd.leetcode.textA;

import java.util.*;

public class SubdomainVisits {

    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> list = new ArrayList<>();
        if (cpdomains.length == 0) {
            return list;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String s : cpdomains) {
            int index = s.indexOf(" ");
            int num = Integer.valueOf(s.substring(0, index));
            s = s.substring(index+1);
            while ((index = s.indexOf(".")) != -1) {
                put(map, s, num);
                s = s.substring(index+1);
            }
            put(map, s, num);
        }
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            Integer v = map.get(k);
            list.add(v + " " + k);
        }
        return list;
    }

    private void put(Map<String, Integer> map, String k, int num) {
        Integer v = map.get(k);
        if (v == null) {
            map.put(k, num);
        } else {
            map.put(k, num + v);
        }
    }

}
