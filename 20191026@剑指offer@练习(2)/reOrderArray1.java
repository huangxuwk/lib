package com.dl.test2;

import java.util.ArrayList;
import java.util.List;

/*
 * ����һ���������飬ʵ��һ�����������������������ֵ�˳��
 * ʹ�����е�����λ�������ǰ�벿�֣����е�ż��λ������ĺ�벿�֣�
 * ����֤������������ż����ż��֮������λ�ò��䡣
 */
public class reOrderArray1 {

    public void reOrderArray(int [] array) {
    	int length = array.length;
        if (length <= 0) {
        	return;
        }
        int oddCount = 0;
        int evenCount = 0;
        for (int i = 0; i < length; i++) {
        	if (array[i] % 2 == 0) {
        		evenCount++;
        	} else {
        		oddCount++;
        	}
        }
        List<Integer> oddList = new ArrayList<Integer>();
        List<Integer> evenList = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
        	if (array[i] % 2 == 0) {
        		evenList.add(array[i]);
        	} else {
        		oddList.add(array[i]);
        	}
        }
        for (int i = 0; i < oddCount; i++) {
        	array[i] = oddList.get(i);
        }
        for (int i = 0; i < evenCount; i++) {
        	array[i + oddCount] = evenList.get(i);
        }
    }
    
    public static void main(String[] args) {
    	new reOrderArray1().reOrderArray(new int[] {0, 2, 1, 4, -1, 3});
    }
}
