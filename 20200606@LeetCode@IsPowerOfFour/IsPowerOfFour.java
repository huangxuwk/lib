package com.dl.test9;

public class IsPowerOfFour {

    public boolean isPowerOfFour(int num) {
        if (num < 1) {
            return false;
        }
        // 如果是4的幂次方，那一定是2的幂次方，而如果是4的幂，那唯一的1应该在二进制的奇数位上
        // 因此可以与1010 1010 ——— 进行与运算，如果相同则是4的幂，如果不同（为0）则不是4的幂
        if (((num & (num - 1)) == 0) && ((num & 0x55555555) == num)) {
            return true;
        }
        return false;
    }
    
}
