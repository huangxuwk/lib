package com.dl.textK;

public class ValidMountainArray {

    public boolean validMountainArray(int[] A) {
        int len = A.length;
        if (len < 3) {
            return false;
        }
        // �ҵ�ɽ��
        int max = 0;
        int mi = 0;
        for (int i = 0; i < len; i++) {
            if (A[i] > max) {
                max = A[i];
                mi = i;
            }
        }
        if (mi == 0 || mi == len - 1) {
            return false;
        }
        // �ж�ɽǰ
        int pre = max;
        for (int i = mi - 1; i >= 0; i--) {
            if (A[i] >= pre) {
                return false;
            }
            pre = A[i];
        }
        // �ж�ɽ��
        pre = max;
        for (int i = mi + 1; i < len; i++) {
            if (A[i] >= pre) {
                return false;
            }
            pre = A[i];
        }
        return true;
    }
	
}
