package src.com.jd.leetcode.textD;

import java.util.HashSet;
import java.util.Set;

public class NumUniqueEmails {

    public int numUniqueEmails(String[] emails) {
        if (emails.length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for (String e : emails) {
            StringBuilder sb = new StringBuilder();
            char[] ec = e.toCharArray();
            for (int i = 0; i < ec.length; i++) {
                if (ec[i] == '.') {
                    continue;
                } else if (ec[i] == '+') {
                    sb.append(e.substring(e.indexOf('@')));
                    break;
                } else if (ec[i] == '@'){
                    sb.append(e.substring(i));
                    break;
                } else {
                    sb.append(ec[i]);
                }
            }
            // System.out.println(sb.toString());
            set.add(sb.toString());
        }
        return set.size();
    }

}
