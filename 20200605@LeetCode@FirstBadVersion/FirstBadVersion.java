package com.dl.test8;

public class FirstBadVersion {

    public int firstBadVersion(int n) {
        int left = 0;
        int right = n;
        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    // ���汾�Ƿ���ȷ
	private boolean isBadVersion(int mid) {
		return false;
	}
	
}
