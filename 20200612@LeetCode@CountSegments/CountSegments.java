package com.dl.text1;

public class CountSegments {

    public int countSegments(String s) {
        int count = 0;
        String[] sa = s.split(" ");
        for (String str : sa) {
            if (!str.equals("")) {
                count++;
            }
        }
        return count;
    }
	
}
