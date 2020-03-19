package com.dl.test3;

public class FindMedianSortedArrays {

	public FindMedianSortedArrays() {
	}
	
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int sumLen = nums1.length + nums2.length;
        int[] tmp = new int[sumLen];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j]) {
                tmp[index++] = nums1[i++];
            } else {
                tmp[index++] = nums2[j++];
            }
        }
        System.out.println(i + " " + j);
        for (; index < sumLen && i < len1; index++,i++) {
            tmp[index] = nums1[i];
        }
        for (; index < sumLen && j < len2; index++,j++) {
            tmp[index] = nums2[j];
        }
        for (int k : tmp) {
			System.out.println(k);
		}
        if (sumLen % 2 == 1) {
            return tmp[sumLen / 2] * 1.0; 
        } 
        return (tmp[sumLen / 2] + tmp[(sumLen - 1) /2]) / 2.0;
    }
    
    public static void main(String[] args) {
		int[] a = new int[] {1,2};
		int[] b = new int[] {3,4};
		System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(a, b));
	}
}
