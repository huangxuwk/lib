package com.dl.textK;

import java.util.ArrayList;
import java.util.List;

public class LargeGroupPositions {

    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> llist = new ArrayList<>();
        if (S.length() == 0) {
            return llist;
        }
        // ��������Ϊ ��aaa�� �ȵ�һ�ϴ����
        S = S + "A";
        char[] sc = S.toCharArray();
        char pre = sc[0];
        int start = 0;
        int end = 0;
        for (int i = 1; i < S.length(); i++) {
            if (sc[i] == pre) {
                end++;
            } else {
                if (end - start >= 2) {
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(end);
                    llist.add(list);
                }
                start = end =  i;
                pre = sc[i];
            }
        }       
        return llist;
    }
	
}
