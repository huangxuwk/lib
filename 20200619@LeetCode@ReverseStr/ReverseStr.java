package com.dl.text3;

public class ReverseStr {
    StringBuilder sb = new StringBuilder();

    public String reverseStr(String s, int k) {
        reverse(s, k);
        return sb.toString();
    }

    private void reverse(String s, int k) {
        if (s.length() < k) {
            sb.append(new StringBuilder(s).reverse().toString());
        } else if (s.length() <= 2 * k) {
            sb.append(new StringBuilder(s.substring(0, k)).reverse().toString());
            sb.append(s.substring(k));
        } else {
            sb.append(new StringBuilder(s.substring(0, k)).reverse().toString());
            // System.out.println(sb.toString());
            sb.append(s.substring(k, 2 * k));
            // System.out.println(sb.toString());
            reverse(s.substring(2 * k), k);           
        }
    }
}
