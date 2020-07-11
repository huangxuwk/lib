package com.dl.textJ;

public class ShortestToChar {
    public int[] shortestToChar(String S, char C) {
        int[] re = new int[S.length()];
        if (S.length() == 0) {
            return re;
        }
        char[] sc = S.toCharArray();
        for (int i = 0; i < sc.length; i++) {
            re[i] = Math.min(preFindE(sc, i, C), nextFindE(sc, i, C));
        }
        return re;
    }

    private int preFindE(char[] sc, int index, char C) {
        for (int i = index; i >= 0; i--) {
            if (sc[i] == C) {
                return index - i;
            }
        }
        return sc.length + 1;
    }

    private int nextFindE(char[] sc, int index, char C) {
        for (int i = index; i < sc.length; i++) {
            if (sc[i] == C) {
                return i - index;
            }
        }
        return sc.length + 1;
    }
}
