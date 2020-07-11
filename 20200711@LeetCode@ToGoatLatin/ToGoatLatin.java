package com.dl.textJ;

import java.util.HashSet;
import java.util.Set;

public class ToGoatLatin {
    private static final Set<Character> set;
    static {
        set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
    }

    public String toGoatLatin(String S) {
        if (S.length() == 0) {
            return S;
        }
        String[] sa = S.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sa.length; i++) {
            char c = sa[i].charAt(0);
            if (set.contains(c)) {
                sb.append(sa[i]).append("ma");
            } else {
                sb.append(sa[i].substring(1)).append(c).append("ma");
            }
            addA(sb, i + 1);
            if (i + 1 < sa.length) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private void addA(StringBuilder sb, int count) {
        for (int i = 0; i < count; i++) {
            sb.append("a");
        }
    }
    
}
