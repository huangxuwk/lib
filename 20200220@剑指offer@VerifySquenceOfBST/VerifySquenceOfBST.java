package com.dl.test1;

public class VerifySquenceOfBST {

	public VerifySquenceOfBST() {
	}
	
    public boolean verifySquenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length == 0) {
        	return false;
        }
        if (sequence.length == 1) {
        	return true;
        }
    	
    	return judge(sequence, 0, sequence.length - 1);
    }
    
    private boolean judge(int[] se, int start, int end) {
    	if (start >= end) {
    		return true;
    	}
    	int index = start;
    	while (index <= end && se[index] < se[end]) {
    		index++;
    	}
    	System.out.println(index);
    	for (int i = index + 1; i < end; i++) {
    		if (se[i] <= se[end]) {
    			return false;
    		}
    	}
    	return judge(se, start, index-1) && judge(se, index, end-1);
    }
    
    public static void main(String[] args) {
    	int[] a = new int[] {4,8,6,12,16,14,10};
    	
		System.out.println(new VerifySquenceOfBST().verifySquenceOfBST(a));
	}
}
