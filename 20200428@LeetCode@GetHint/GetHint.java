package com.dl.test9;

public class GetHint {

    // 奶牛和公牛可先当做一类，然后进行细分，一次遍历查看有多少公牛
    // 并计数每个数字对应的个数，min()可以求得奶牛+公牛的总数
    // 那么知道了公牛和总数，就可以求得奶牛
    public String getHint(String secret, String guess) {
        char[] sc = secret.toCharArray();
        char[] gc = guess.toCharArray();
        int[] si = new int[10];
        int[] gi = new int[10];
        int Ac = 0;
        int Bc = 0;
        for (int i = 0; i < sc.length; i++) {
            si[sc[i] - '0']++;
            gi[gc[i] - '0']++;
            Ac += sc[i] == gc[i] ? 1 : 0;    
        }
        for (int i = 0; i < 10; i++) {
            Bc += Math.min(si[i], gi[i]);
        }
        return Ac + "A" + (Bc - Ac) + "B";
    }
	
}
