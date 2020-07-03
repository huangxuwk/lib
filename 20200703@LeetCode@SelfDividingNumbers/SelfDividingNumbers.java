package com.dl.textI;

import java.util.ArrayList;
import java.util.List;

public class SelfDividingNumbers {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        if (left > right) {
            return list;
        }
        for (int i = left; i <= right; i++) {
            if (isSelfDividing(i)) {
                list.add(i);
            }
        }
        return list;
    }

    private boolean isSelfDividing(int num) {
        int so = num;
        while (num > 0) {
            int bit = num % 10;
            if (bit == 0 || so % bit != 0) {
                return false;
            }
            num /= 10;
        }
        return true;
    }
	
}
