package fr.ninauve.renaud.leetcode.rotatelist;

// https://leetcode.com/problems/rotate-list/
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        int length = makeCircularList(head);
        int shift = k % length;
        int linkToCut = length - shift;
        return cutLink(head, linkToCut);
    }

    int makeCircularList(ListNode head) {
        int length = 1;
        ListNode node = head;
        while (node.next != null) {
            node = node.next;
            length++;
        }
        node.next = head;
        return length;
    }

    ListNode cutLink(ListNode head, int nth) {
        ListNode node = head;
        for (int i = 0; i < nth - 1; i++) {
            node = node.next;
        }
        ListNode newHead = node.next;
        node.next = null;
        return newHead;
    }
}
