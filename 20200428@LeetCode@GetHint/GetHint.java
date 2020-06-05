package com.dl.test9;

public class GetHint {

    // ��ţ�͹�ţ���ȵ���һ�࣬Ȼ�����ϸ�֣�һ�α����鿴�ж��ٹ�ţ
    // ������ÿ�����ֶ�Ӧ�ĸ�����min()���������ţ+��ţ������
    // ��ô֪���˹�ţ���������Ϳ��������ţ
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
