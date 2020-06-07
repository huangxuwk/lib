package com.dl.test9;

public class IsPerfectSquare {

    public boolean isPerfectSquare(int num) {
        int left = 1;
        int right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int r = num / mid;
            if (r == mid) {
                if (num % mid == 0) {
                    return true;
                }
                left = mid + 1;
            } else if (r < mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
	
}
