package com.dl.text6;

public class FindMaxAverage {

    public double findMaxAverage(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || k > len) {
            return 0.0;
        }
        int left = 0;
        int right = k;
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        long max = sum;
        while (right < len) {
            sum -= nums[left];
            sum += nums[right];
            if (sum > max) {
                max = sum;
            }
            right++;
            left++;
        }
        System.out.println(sum);        
        return max * 1.0 / k;
    }
	
}
