package src.com.jd.leetcode.textC;

public class ReverseOnlyLetters {

    public String reverseOnlyLetters(String S) {
        int len = S.length();
        if (len == 0) {
            return S;
        }
        int left = 0;
        int right = len - 1;
        char[] sc = S.toCharArray();
        while (left < right) {
            while (left < right && !(( sc[left] >= 'A' && sc[left] <= 'Z')
                    || (sc[left] >= 'a' && sc[left] <= 'z'))) {
                left++;
            }
            while (left < right && !(( sc[right] >= 'A' && sc[right] <= 'Z')
                    || (sc[right] >= 'a' && sc[right] <= 'z'))) {
                right--;
            }
            char temp = sc[left];
            sc[left++] = sc[right];
            sc[right--] = temp;
        }
        return new String(sc);
    }

}
