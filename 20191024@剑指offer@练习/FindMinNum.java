package com.dl.test;

import java.util.Arrays;

/*
��һ�������ʼ�����ɸ�Ԫ�ذᵽ�����ĩβ�����ǳ�֮Ϊ�������ת��
����һ���ǵݼ�����������һ����ת�������ת�������СԪ�ء�
��������{3,4,5,1,2}Ϊ{1,2,3,4,5}��һ����ת�����������СֵΪ1��
NOTE������������Ԫ�ض�����0���������СΪ0���뷵��0��
 */
public class FindMinNum {
    public int minNumberInRotateArray(int [] array) {
        if (array.length == 0) {
            return 0;
        }
        Arrays.sort(array);
		return array[0];
    }
    
    public static void main(String[] args) {
		System.out.println(new FindMinNum().minNumberInRotateArray(new int[]{1, 2, 2, 1, 1}));
	}
}
