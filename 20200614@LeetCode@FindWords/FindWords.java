package com.dl.text2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindWords {
    public static final int STYLE_1 = 1;
    public static final int STYLE_2 = 2;
    public static final int STYLE_3 = 3;

    private static Set<Character> set1;
    private static Set<Character> set2;
    private static Set<Character> set3;
    static {
        set1 = new HashSet<>();
        String s1 = "QWERTYUIOPqwertyuiop";
        char[] c1 = s1.toCharArray();
        for (char c : c1) {
            set1.add(c);
        }
        set2 = new HashSet<>();
        String s2 = "ASDFGHJKLasdfghjkl";
        char[] c2 = s2.toCharArray();
        for (char c : c2) {
            set2.add(c);
        } 
        set3 = new HashSet<>();
        String s3 = "ZXCVBNMzxcvbnm";
        char[] c3 = s3.toCharArray();
        for (char c : c3) {
            set3.add(c);
        }        
    }

    public String[] findWords(String[] words) {
        List<String> list = new ArrayList<>();
        for (String s : words) {
            char[] sc = s.toCharArray();
            int style = -1;
            if (set1.contains(sc[0])) {
                style = STYLE_1;
            } else if (set2.contains(sc[0])) {
                style = STYLE_2;
            } else if (set3.contains(sc[0])) {
                style = STYLE_3;
            }
            boolean ok = true;
            for (char c : sc) {
                if (style == STYLE_1 && !set1.contains(c)) {
                    ok = false;
                    break;
                } else if (style == STYLE_2 && !set2.contains(c)) {
                    ok = false;
                    break;
                } else if (style == STYLE_3 && !set3.contains(c)) {
                    ok = false;
                    break;
                }                
            }
            if (ok) {
                list.add(s);
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
