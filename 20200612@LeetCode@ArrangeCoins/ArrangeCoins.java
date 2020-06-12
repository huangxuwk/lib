package com.dl.text1;

public class ArrangeCoins {

// public int arrangeCoins(int n) {
//     int left = 0;
//     int right = n;
//     while (left <= right) {
//         int mid = left +  (right - left) / 2;
//         int re = (1 + mid) * mid / 2;
//         if (re > n) {
//             right = mid - 1;
//         } else {
//             int temp = (2 + mid) * (mid + 1) / 2;
//             if (re == n || (re < n && temp > n)) {
//                 return mid;
//             } else {
//                 left = mid + 1;
//             }
//         }
//     }
//     return -1;
// }
	
	public int arrangeCoins(int n) {
		return (int)(Math.sqrt(2) * Math.sqrt(n + 0.125) - 0.5);
	}
	
}
