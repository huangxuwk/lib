package com.dl.test2;

/*
 * ���ǿ�����2*1��С���κ��Ż�������ȥ���Ǹ���ľ��Ρ�
 * ������n��2*1��С�������ص��ظ���һ��2*n�Ĵ���Σ�
 * �ܹ��ж����ַ�����
 */
public class RectCover1 {
	private static boolean sign = false;
	
    public int RectCover(int target) {
    	if (sign == false) {
    		if (target == 0) {
    			return 0;
    		}
    	}
    	sign = true;
    	if (target == 0) {
    		return 1;
    	} else if (target < 0) {
    		return 0;
    	}
    	int result = 0;
    	result += RectCover(target - 1);
    	result += RectCover(target - 2);
    	
		return result;
    }
    
    public static void main(String[] args) {
		System.out.println(new RectCover1().RectCover(0));
	}
}
