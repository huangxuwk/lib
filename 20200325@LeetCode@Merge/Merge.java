package com.dl.test3;

public class Merge {

	public Merge() {
	}
	
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pos = m + n - 1;
        int s1 = m - 1;
        int s2 = n - 1;
        while (s1 >= 0 && s2 >= 0) {
            if (nums1[s1] >= nums2[s2]) {
                nums1[pos--] = nums1[s1--];
            } else {
                nums1[pos--] = nums2[s2--];
            }
        }

        while (s2 >= 0) {
            nums1[pos--] = nums2[s2--];
        }

    }
}
