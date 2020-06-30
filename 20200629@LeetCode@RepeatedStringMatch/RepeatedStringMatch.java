package com.dl.text7;

public class RepeatedStringMatch {

    public int repeatedStringMatch(String A, String B) {
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == B.charAt(0)) {
                int ai = i;
                int bj = 0;
                // Ñ­»·´ÎÊý
                int count = 1;
                while (A.charAt(ai) == B.charAt(bj)) {
                    ai++;
                    bj++;
                    if (bj >= B.length()) {
                        return count;
                    }
                    if (ai >= A.length()) {
                        ai = 0;
                        count++;
                    }
                }
            }
        }
        return -1;
    }
	
}
