package com.dl.test;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {

	public LengthOfLongestSubstring() {
	}
	
	public int solution(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
            	System.out.println(set.contains(s.charAt(j)));
            	System.out.println("s.charAt(j) " + s.charAt(j));
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
            	System.out.println("i++");
            	set.remove(s.charAt(i++));
//            	i++;
            }
        }
        for (Character character : set) {
			System.out.println(character);
		}
        return ans;
	}
	
	static class test {
		public static void main(String[] args) {
			System.out.println(new LengthOfLongestSubstring().solution("pwwe"));
		}
	}
}
