package src.com.jd.leetcode.textH;

public class MaxArea {

    public int maxArea(int[] height) {
        int len = height.length;
        if (len == 0) {
            return 0;
        }
        int left = 0;
        int right = len - 1;
        int maxArea = 0;
        while (left < right) {
            int lH = height[left];
            int rH = height[right];
            int area = Math.min(lH, rH) * (right - left);
            maxArea = maxArea > area ? maxArea : area;
            if (lH > rH) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }

}
