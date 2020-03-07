package com.dl.test4;

import java.util.LinkedList;

public class FirstAppearingOnce {
    private int[] chars = new int[128];
    private LinkedList<Character> queue = new LinkedList<>();
    
    //Insert one char from stringstream
    public void Insert(char ch)
    {
        if (chars[ch - ' '] == 0) {
            queue.offer(ch);
        }
        chars[ch - ' ']++;
    }
  //return the first appearence once char in current stringstream
    public char firstAppearingOnce()
    {
        while (!queue.isEmpty() && chars[queue.peek() - ' '] >= 2) {
            queue.poll();
        }
        if (!queue.isEmpty()) {
            return queue.peek();
        }
        
        return '#';
    }
}
