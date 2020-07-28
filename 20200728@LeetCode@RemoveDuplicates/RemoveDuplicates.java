package src.com.jd.leetcode.textG;

import java.util.Stack;

public class RemoveDuplicates {

    public String removeDuplicates(String S) {
        int len = S.length();
        if (len == 0) {
            return "";
        }
        Stack<Character> stack = new Stack<>();
        char[] cs = S.toCharArray();
        for (char c : cs) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (c == stack.peek()) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

}
