package com.dl.text3;

public class DetectCapitalUse {

    public boolean detectCapitalUse(String word) {
        if (word.length() == 0) {
            return true;
        }
        char[] wa = word.toCharArray();
        boolean flag = false;
        if ('Z' - wa[0] >= 0) {
            flag = true;
        }
        if (flag) {
            // Ê××ÖÄ¸´óĞ´
            int smallCount = 0;
            for (int i = 1; i < wa.length; i++) {
                if ('Z' - wa[i] < 0) {
                    smallCount++;
                }                
            }
            if (smallCount == wa.length - 1 || smallCount == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            for (int i = 1; i < wa.length; i++) {
                if ('Z' - wa[i] >= 0) {
                    return false;
                }                
            }            
        }
        return true;
    }
	
}
