package com.dl.test9;

import java.util.HashSet;
import java.util.Set;

public class ReverseVowels {
    private static Set<Character> set;
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

    public String reverseVowels(String s) {
        if (s.length() == 0) {
            return "";
        }
        char[] ca = s.toCharArray();
        int left = 0;
        int right = ca.length - 1;
        while (left < right) {
            while (left < right && !set.contains(ca[left])) {
                left++;
            }
            while (left < right && !set.contains(ca[right])) {
                right--;
            }
            if (left < right) {
                char temp = ca[left];
                ca[left++] = ca[right];
                ca[right--] = temp;
            }
        }
        return new String(ca);
    }
}
