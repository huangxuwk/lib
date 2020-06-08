package com.dl.test10;

public class GuessNumber {

    public int guessNumber(int n) {
        int left = 0;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int re = guess(mid);
            if (re == -1) {
                right = mid - 1;
            } else if (re == 1) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return 0;
    }
    
    /** 
     * Forward declaration of guess API.
     * @param  num   your guess
     * @return 	     -1 if num is lower than the guess number
     *			      1 if num is higher than the guess number
     *               otherwise return 0
     * int guess(int num);
     */
	private int guess(int mid) {
		return 0;
	}
	
}
