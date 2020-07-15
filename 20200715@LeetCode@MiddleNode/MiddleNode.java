package src.com.jd.leetcode.textB;

import src.com.jd.leetcode.util.ListNode;

public class MiddleNode {

    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return head;
        }
        int fast = 1;
        int slow = 1;
        ListNode node = head;
        ListNode tar = head;
        while (node != null) {
            node = node.next;
            fast++;
            if ((fast + 1) / 2 > slow) {
                tar = tar.next;
                slow++;
            }
        }
        return tar;
    }

}
