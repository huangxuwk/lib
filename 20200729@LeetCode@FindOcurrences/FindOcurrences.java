package src.com.jd.leetcode.textG;

import java.util.ArrayList;
import java.util.List;

public class FindOcurrences {

    public String[] findOcurrences(String text, String first, String second) {
        if (text.length() == 0) {
            return new String[]{};
        }
        List<String> list = new ArrayList<>();
        String[] sa = text.split(" ");
        boolean sign = false;
        int len = sa.length;
        for (int i = 0; i < len; i++) {
            if (sa[i].equals(first)) {
                sign = true;
            } else if (sa[i].equals(second) && sign
                    && i + 1 < len) {
                // System.out.println(i+1);
                list.add(sa[i + 1]);
                sign = false;
            } else {
                sign = false;
            }
        }
        String[] re = new String[list.size()];
        int index = 0;
        for (String s : list) {
            re[index++] = s;
        }
        return re;
    }

}
