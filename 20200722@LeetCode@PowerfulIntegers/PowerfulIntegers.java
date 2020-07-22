package src.com.jd.leetcode.textE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PowerfulIntegers {

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <1000; i++) {
            int nx = (int)Math.pow(x, i);
            if (nx > bound) {
                break;
            }
            for (int j = 0; j < 1000; j++) {
                int s = nx + (int)Math.pow(y, j);
                if (s > bound) {
                    break;
                }
                if (!set.contains(s)) {
                    list.add(s);
                    set.add(s);
                }
            }
        }
        return list;
    }

}
