package com.hx.about_sort.test;

import java.util.ArrayList;
import java.util.List;

public class MyArray <T> {
	private ArrayList<T> array;
	
	public MyArray() {
		array = new ArrayList<>();
	}
	
	public void add(T element) {
		array.add(element);
	}
	
	public void sort(Compare<T> compare) {
		for (int i  = 0; i < array.size(); i++) {
			for (int j = i + 1; j < array.size(); j++) {
				if (compare.compare(array.get(i), array.get(j)) > 0) {
					T tmp;
					tmp = array.get(i);
					array.set(i, array.get(j));
					array.set(j, tmp);
				}
			}
		}
	}
	
	public List<T> getList() {
		return array;
	}
	
}
