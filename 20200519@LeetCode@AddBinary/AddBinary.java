package com.dl.test5;

public class AddBinary {
	
    public String addBinary(String a, String b) {
        if (a == null || b == null) {
            return "";
        }
        if (a.equals("0") && b.equals("0")) {
            return "0";
        }
        char[] ac = a.toCharArray();
        char[] bc = b.toCharArray();
        int al = ac.length;
        int bl = bc.length;
        int length = al > bl ? al : bl;
        int carry = 0;
        char[] result = new char[length];
        int ai = al - 1, bi = bl - 1, ri = length - 1;
        for (; ai >= 0 && bi >= 0 && ri >= 0; ai--, bi--, ri--) {
        	int ta = ac[ai] - 48;
        	int tb = bc[bi] - 48;
        	System.out.println("ta : " + ta + " tb : " + tb);
        	int re = ta + tb + carry;
        	result[ri] = (char) (re % 2 + 48);
        	System.out.println("result[ri] : " + result[ri]);
        	carry = re / 2;
        	System.out.println("carry : " + carry);
        }
        if (bi >= 0) {
        	for (; bi >= 0; bi--, ri--) {
            	int tb = bc[bi] - 48;
            	int re = tb + carry;
            	result[ri] = (char) (re % 2 + 48);
            	System.out.println("result[ri] : " + result[ri]);
            	carry = re / 2;
            	System.out.println("carry : " + carry);        		
        	}
        } else if (ai >= 0) {
        	for (; ai >= 0; ai--, ri--) {
            	int ta = ac[ai] - 48;
            	int re = ta + carry;
            	result[ri] = (char) (re % 2 + 48);
            	System.out.println("result[ri] : " + result[ri]);
            	carry = re / 2;
            	System.out.println("carry : " + carry);        		
        	}	
        }
        String restr = new String(result);
        if (carry == 1) {
        	restr = "1" + restr;
        }
		return restr;
    }
    
    public static void main(String[] args) {
		System.out.println(new AddBinary().addBinary("1", "111"));
	}

}
