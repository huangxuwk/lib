package com.dl.text7;

public class CalPoints {

    public int calPoints(String[] ops) {
        if (ops == null || ops.length == 0) {
            return 0;
        }
        int[] sign = new int[ops.length];
        int index = 0;
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("+")) {
                sign[index] = sign[index - 1] + sign[index - 2];
                index++;
            } else if (ops[i].equals("D")) {
                sign[index] = 2 * sign[index - 1];
                index++;
            } else if (ops[i].equals("C")) {
                sign[--index] = 0;
            } else {
                sign[index] = Integer.valueOf(ops[i]);
                index++;
            }
        }
        int re = 0;
        for (int s : sign) {
            re += s;
        }
        return re;
    }
	
}
