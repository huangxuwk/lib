package com.dl.textJ;

public class NumJewelsInStones {

    public int numJewelsInStones(String J, String S) {
        if (J.length() == 0 || S.length() == 0) {
            return 0;
        }
        boolean[] map = new boolean[58];
        for (char c : J.toCharArray()) {
            map[c - 'A'] = true;
        }
        int count = 0;
        for (char c : S.toCharArray()) {
            if (map[c - 'A'] == true) {
                count++;
            }
        }
        return count;
    }
	
}
