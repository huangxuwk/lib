package com.hx.about_sort.test;

public class Demo {
	
	public static void main(String[] args) {
		MyArray<String> arr = new MyArray<>();
		arr.add("sdf");
		arr.add("sasdff");
		arr.add("asdfgfdsdf");
		arr.add("fghsdf");
		arr.add("jfghjsdf");
	
		arr.sort(new Compare<String>() {
			@Override
			public int compare(String one, String other) {
				return one.compareTo(other);
			}
		});
	
		System.out.println(arr.getList());
	}
}
