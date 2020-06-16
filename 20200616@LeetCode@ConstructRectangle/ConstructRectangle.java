package com.dl.text2;

public class ConstructRectangle {

    public int[] constructRectangle(int area) {
        double sqrt = Math.sqrt(area);
        int[] re = new int[2];
        if (sqrt - (int)sqrt == 0) {
            re[0] = (int)sqrt;
            re[1] = (int)sqrt;
            return re;
        }         
        for (int i = (int)sqrt; i > 0; i--) {
            if (area % i == 0) {
                re[0] = area / i;
                re[1] = i;
                return re;                
            }
        }
        return re;
    }
	
}
