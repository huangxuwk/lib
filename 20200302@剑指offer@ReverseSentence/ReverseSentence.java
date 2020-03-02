package com.dl.test3;

public class ReverseSentence {

	public ReverseSentence() {
	}
	
    public String reverseSentence(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        String[] array = str.split(" ");
        if (array.length == 0) {
            return str;
        }
        StringBuilder builder = new StringBuilder();
        for (int index = array.length - 1; index >= 0; index--) {
            builder.append(array[index]);
            if (index != 0) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}
