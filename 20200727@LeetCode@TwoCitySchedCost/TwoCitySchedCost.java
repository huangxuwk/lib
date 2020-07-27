package src.com.jd.leetcode.textF;

import java.util.PriorityQueue;

public class TwoCitySchedCost {

    public int twoCitySchedCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int sum = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int[] c : costs) {
            sum += c[0];
            queue.offer(c[1] - c[0]);
        }
        while (queue.size() > costs.length / 2) {
            sum += queue.poll();
        }
        return sum;
    }

}
