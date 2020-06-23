package com.dl.text5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindRestaurant {

    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1.length == 0 || list2.length == 0) {
            return new String[]{};
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        int minLen = Integer.MAX_VALUE;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            Integer v = map.get(list2[i]);
            if (v != null) {
                if (i + v < minLen) {
                    minLen = i + v;
                    list.clear();
                    list.add(list2[i]);
                } else if (i + v == minLen) {
                    list.add(list2[i]);
                }
            }
        }
        String[] re = new String[list.size()];
        int index = 0;
        for (String s : list) {
            re[index++] = s;
        }
        return re;
    }
	
}
