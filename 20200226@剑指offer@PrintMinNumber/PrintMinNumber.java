package com.dl.test2;

import java.util.Arrays;
import java.util.Comparator;

public class PrintMinNumber {

	public PrintMinNumber() {
	}
	
    public String printMinNumber(int [] numbers) {
    	int length;
    	if (numbers == null || (length = numbers.length) == 0) {
    		return "";
    	}
    	if (length == 1) {
    		return String.valueOf(numbers[0]);
    	}
    	String[] string = new String[length];
    	for (int i = 0; i < numbers.length; i++) {
			string[i] = String.valueOf(numbers[i]);
		}
    	Arrays.sort(string, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				StringBuilder b1 = new StringBuilder(o1).append(o2);
				StringBuilder b2 = new StringBuilder(o2).append(o1);
				return b1.toString().compareTo(b2.toString());
			}
		});
    	StringBuilder builder = new StringBuilder();
    	for (String str : string) {
			builder.append(str);
		}
    	
    	return builder.toString();
    }
    
    public static void main(String[] args) {
    	int[] numbers = new int[] {3, 32, 321};
		System.out.println(new PrintMinNumber().printMinNumber(numbers));
	}
}
