package com.dl.test2;

/*
 * ����һ��������������������Ʊ�ʾ��1�ĸ��������и����ò����ʾ��
 */
public class NumberOf11 {

    public int NumberOf1(int n) {
    	int flag = 1;
    	int count = 0;
    	
    	if (n <= -1) {
    		n = -n;
    	}
    	
    	while (n != 0) {
    		if ((n & flag) == 1) {
    			count++;
    		}
    		// �������ƣ��������λ��1�����ǲ�0
    		// >>> : �޷������ƣ��߼�����
    		n = n >>> 1;
    	}
    	
		return count;
    }
    
    public static void main(String[] args) {
		System.out.println(new NumberOf11().NumberOf1(-7));
	}
}
