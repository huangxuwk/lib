package com.dl.text7;

public class ValidPalindrome {

    public boolean validPalindrome(String s) {
        return vaild(s, 0, s.length() - 1, 1);
    }

    private boolean vaild(String s, int left, int right, int chance) {
        if (left > right) {
            return true;
        }
        if (s.charAt(left) == s.charAt(right)) {
            return vaild(s, left+1, right-1, chance);
        } else {
            // 只有一次修改的机会
            if (chance == 0) {
                return false;
            }
            // 尝试删除左边或右边的字符，再验证是否为回文串
            return vaild(s, left + 1, right, chance - 1) 
                    || vaild(s, left, right - 1, chance - 1);
        }
    }
	
}
