package com.dl.test2;

/*
 * һֻ����һ�ο�������1��̨�ף�Ҳ��������2��������Ҳ��������n����
 * �����������һ��n����̨���ܹ��ж�����������
 */
public class JumpFloorII {
	
	/*
	 * 1 : 1
	 * 2 : 2
	 * 3 : 111 12 21 3
	 */
    public int JumpFloor(int target) {
    	if (target == 0) {
    		return 1;
    	} else if (target < 0) {
    		return 0;
    	}
    	
    	int result = 0;
    	for (int i = 1; i <= target; i++) {
    		result += JumpFloor(target - i);
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
		System.out.println(new JumpFloorII().JumpFloor(3));
	}
}
