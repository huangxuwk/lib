package com.dl.test3;

public class IsContinuous {
	
	public IsContinuous() {
	}
	
    public boolean isContinuous(int [] numbers) {
        int length;
        if (numbers == null || (length = numbers.length) < 5) {
            return false;
        }
        int max = -1;
        int min = 14;
        int flag = 0;
        for (int i = 0; i < length; i++) {
            if (numbers[i] == 0) {
                continue;
            }
            // 比较该位上是否为1，若为1，说明存在相同数字
            if (((flag >> numbers[i]) & 1) == 1) {
                return false;
            }
            // 若该数为独一无二的，则置位
            flag |= (1 << numbers[i]);
            if (numbers[i] > max) {
                max = numbers[i];
            }
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        if (max - min >= 5) {
            return false;
        }
        return true;
    }

}
