package src.com.jd.leetcode.textE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddToArrayForm {

    public List<Integer> addToArrayForm(int[] A, int K) {
        int len = A.length;
        List<Integer> list = new ArrayList<>();
        reverse(A);
        int i = 0;
        int carry = 0;
        while (K > 0) {
            int bit = K % 10;
            int val = 0;
            if (i < len) {
                val = A[i];
            }
            list.add((bit + val + carry) % 10);
            carry = (bit + val + carry) / 10;
            i++;
            K /= 10;
        }
        while (i < len) {
            if (carry > 0) {
                list.add((A[i] + carry) % 10);
                carry = (A[i] + carry) / 10;
            } else {
                list.add(A[i]);
            }
            i++;
        }
        if (carry > 0) {
            list.add(carry);
        }
        Collections.reverse(list);
        return list;
    }

    private void reverse(int[] A) {
        int left = 0;
        int right = A.length - 1;
        while (left < right) {
            int temp = A[left];
            A[left++] = A[right];
            A[right--] = temp;
        }
    }
}
