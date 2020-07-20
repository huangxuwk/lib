package src.com.jd.leetcode.textD;

import java.util.HashSet;
import java.util.Set;

public class RepeatedNTimes {

    public int repeatedNTimes(int[] A) {
        Set<Integer> set = new HashSet<>(A.length / 2 + 1);
        for (int i : A) {
            if (!set.add(i)) {
                return i;
            }
        }
        return 0;
    }

}
