package com.dl.test3;

public class MaxProfit {
	
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int delta = 0;
        for (int i = 0; i < prices.length; i++) {
            if (min > prices[i]) {
                min = prices[i];
            } else if (prices[i] - min > delta) {
                delta = prices[i] - min;
            }
        }

        return delta;
    }
}
