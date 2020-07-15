package src.com.jd.leetcode.textB;

import java.util.*;

public class UncommonFromSentences {

    public String[] uncommonFromSentences(String A, String B) {
        if (A.length() == 0 || B.length() == 0) {
            return new String[]{};
        }
        String[] as = A.split(" ");
        String[] bs = B.split(" ");
        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (String s : bs) {
            Integer v = map.get(s);
            if (v == null) {
                map.put(s, 1);
            } else {
                map.put(s, v + 1);
            }
        }
        for (String s : as) {
            Integer v = map.get(s);
            if (v == null) {
                map.put(s, 1);
            } else {
                map.put(s, v + 1);
            }
        }
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            if (map.get(k) == 1) {
                list.add(k);
            }
        }
        String[] re = new String[list.size()];
        int i = 0;
        for (String s : list) {
            re[i++] = s;
        }
        return re;
    }

}
