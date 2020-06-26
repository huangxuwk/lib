package com.dl.text6;

public class JudgeCircle {

    public boolean judgeCircle(String moves) {
        if (moves.length() == 0) {
            return true;
        }
        char[] mc = moves.toCharArray();
        int[] index = new int[]{0, 0};
        for (char c : mc) {
            switch (c) {
                case 'R' : index[0]++;
                break;
                case 'L' : index[0]--;
                break;
                case 'U' : index[1]++;
                break;
                case 'D' : index[1]--;
            }
        }
        for (int i : index) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
	
}
