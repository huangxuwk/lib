package src.com.jd.leetcode.textC;

import java.util.*;

public class NumSpecialEquivGroups {

    public int numSpecialEquivGroups(String[] A) {
        if (A.length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for (String s : A) {
            char[] sa = s.toCharArray();
            List<Character> odd = new ArrayList<>();
            List<Character> even = new ArrayList<>();
            for (int i = 0; i < sa.length; i++) {
                if (i % 2 == 0) {
                    odd.add(sa[i]);
                } else {
                    even.add(sa[i]);
                }
            }
            Collections.sort(odd);
            Collections.sort(even);
            StringBuilder sb = new StringBuilder();
            for (char c : odd) {
                sb.append(c);
            }
            for (char c : even) {
                sb.append(c);
            }
            set.add(sb.toString());
        }
        return set.size();
    }

}
