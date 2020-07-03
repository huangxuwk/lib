package com.dl.textI;

public class IsOneBitCharacter {

    public boolean isOneBitCharacter(int[] bits) {
        if (bits.length == 0) {
            return true;
        }
        if (bits[bits.length - 1] == 1) {
            return false;
        }
        return isOneBit(bits, 0);
    }

    private boolean isOneBit(int[] bits, int index) {
        if (index >= bits.length) {
            return false;
        }
        if (index == bits.length - 1) {
            return true;
        }
        boolean ok = false;
        if (bits[index] == 1) {
            ok = isOneBit(bits, index + 2);
        } else {
            ok = isOneBit(bits, index + 1) == true ? true : ok;
        }
        return ok;
    }
	
}
