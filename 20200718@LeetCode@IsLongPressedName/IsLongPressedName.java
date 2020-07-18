package src.com.jd.leetcode.textD;

public class IsLongPressedName {

    public static boolean isLongPressedName(String name, String typed) {
        int len1 = name.length();
        int len2 = typed.length();
        if (len1 == 0 && len2 == 0) {
            return true;
        }
        if (len1 == 0 || len2 == 0 || len1 > len2) {
            return false;
        }
        char[] nc = name.toCharArray();
        char[] tc = typed.toCharArray();
        int i = 0;
        int j = 0;
        char pre = '1';
        while (i < len1 && j < len2) {
            if (nc[i] == tc[j]) {
                pre = nc[i];
                j++;
                i++;
            } else {
                if (pre == '1' || pre != tc[j]) {
                    return false;
                }
                while (j < len2 && pre == tc[j]) {
                    j++;
                }
            }
        }
        if (j != len2) {
            while (j < len2 && tc[j] == pre) {
                j++;
            }
        }
        if (j != len2 || i != len1) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isLongPressedName("kikcxmvzi","kiikcxxmmvvzz"));
    }

}
