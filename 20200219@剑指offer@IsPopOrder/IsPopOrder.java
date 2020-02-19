package com.dl.test1;

import java.util.Stack;

public class IsPopOrder {
	
	public IsPopOrder() {
	}

    public boolean isPopOrder(int [] pushA,int [] popA) {
    	if (pushA == null || popA == null) {
    		return false;
    	}
    	int pushLen = pushA.length;
    	int popLen = popA.length;
    	if (pushLen == 0 || popLen == 0 || pushLen != popLen) {
    		return false;
    	}
    	Stack<Integer> stack = new Stack<>();
    	int pushIndex = 0;
    	int popIndex = 0;
    	int pop;
    	int push;
    	while (popIndex <= popLen - 1) {
    		pop = popA[popIndex];
    		if (pushIndex == pushLen) {
    			push = stack.pop();
    			if (push != pop) {
    				return false;
    			}
    			popIndex++;
    			continue;
    		}
    		push = pushA[pushIndex];
    		if (push == pop) {
    			popIndex++;
    			pushIndex++;
    		} else {
    			stack.push(push);
    			pushIndex++;
    		}
    	}
    	
    	return true;
    }
    
    public static void main(String[] args) {
		IsPopOrder is = new IsPopOrder();
		int[] pushA = new int[] {1};
		int[] popA = new int[] {2};
		
		boolean ok = is.isPopOrder(pushA, popA);
		System.out.println(ok);
	}
}
