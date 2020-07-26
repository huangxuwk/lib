package com.dl.textL;

import java.util.ArrayList;
import java.util.List;

public class PrefixesDivBy5 {

    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>();
        int len = A.length;
        if (len == 0) {
            return list;
        }
        int num = 0;
        int index = 0;
        while (index < len) {
            num = ((num << 1) + A[index]) % 5;
            if (num % 5 == 0) {
                list.add(true);
            } else {
                list.add(false);
            }
            index++;
        }
        return list;
    }
	
}
