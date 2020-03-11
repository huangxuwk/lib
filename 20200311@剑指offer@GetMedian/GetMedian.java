package com.dl.test5;

import java.util.ArrayList;
import java.util.Collections;

public class GetMedian {
    private ArrayList<Integer> list = new ArrayList<>();
	
	public GetMedian() {
	}

    public void Insert(Integer num) {
        list.add(num);
    }

    public Double getMedian() {
        Collections.sort(list);
        
        int size = list.size();
        if (size % 2 == 1) {
            return Double.valueOf(list.get((size - 1) / 2));
        } else {
            return Double.valueOf((list.get(size / 2) 
                + list.get(size / 2 - 1)) / 2.0);
        }
    }
}
