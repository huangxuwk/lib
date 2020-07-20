package src.com.jd.leetcode.textD;

public class IsAlienSorted {

    public static boolean isAlienSorted(String[] words, String order) {
        int[] index = new int[26];
        // 记录每一个单词在外星的位置权重信息
        for (int i = 0; i < order.length(); i++) {
            index[order.charAt(i) - 'a'] = i;
        }
        // 比较所有单词
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            // 循环比较两个单词的字母
            for (int j = 0; j < word1.length(); j++) {
                Character char1 = word1.charAt(j);
                Character char2;
                if (word2.length() > j) {
                    char2 = word2.charAt(j);
                } else {
                    char2 = null;
                }
                // 因为 第一个单词在，第二个单词不在，第一个大，返回错误
                if (char2 == null) return false;
                if (char1 == char2) {
                    continue;
                } else if (index[char1 - 'a'] > index[char2 - 'a']) {
                    return false;
                } else if (index[char1 - 'a'] < index[char2 - 'a']) {
                    break;
                }
            }
        }
        return true;
    }

}
