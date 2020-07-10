package com.jd.leetcode.textA;

import java.util.HashSet;
import java.util.Set;

public class UniqueMorseRepresentations {
    private static String[] keys = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---",
            "-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-",
            "-.--","--.."};

    public int uniqueMorseRepresentations(String[] words) {
        if (words.length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for (String s : words) {
            char[] cs = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : cs) {
                sb.append(keys[c - 'a']);
            }
            if (!set.contains(sb.toString())) {
                set.add(sb.toString());
            }
        }
        return set.size();
    }
}
