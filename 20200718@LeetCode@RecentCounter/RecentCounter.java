package src.com.jd.leetcode.textD;

import java.util.LinkedList;
import java.util.Queue;

public class RecentCounter {
    private Queue<Integer> queue;;

    public RecentCounter() {
        queue = new LinkedList<>();
    }

    public int ping(int t) {
        queue.offer(t);
        if (t < 3000) {
            return queue.size();
        }
        while (t - queue.peek() > 3000) {
            queue.poll();
        }
        return queue.size();
    }
}
