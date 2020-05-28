package com.dl.test6;

import java.util.ArrayList;
import java.util.List;

public class Generate {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> llist = new ArrayList<>();
        if (numRows == 0) {
            return llist;
        }
        List<Integer> list = new ArrayList<>();
        list.add(1);
        llist.add(list);
        for (int i = 1; i < numRows; i++) {
            List<Integer> last = llist.get(i - 1);
            list = new ArrayList<>();
            llist.add(list);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                    continue;
                }
                list.add(last.get(j - 1) + last.get(j));
            }
        }
        return llist;
    }
	
}
