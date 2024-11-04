package fr.ninauve.renaud.leetcode.reverselinkedlist2;

// https://leetcode.com/problems/reverse-linked-list-ii/
public class ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        if (right == left) {
            return head;
        }

        ListNode previous = null;
        ListNode current = head;
        for (int i = 1; i < left; i++) {
            previous = current;
            current = current.next;
        }

        ListNode reversed = new ListNode(current.val);
        ListNode reversedTail = reversed;
        for (int i = left; i < right; i++) {
            current = current.next;
            reversed = new ListNode(current.val, reversed);
        }

        ListNode result;
        if (previous == null) {
            result = reversed;
        } else {
            result = head;
            previous.next = reversed;
        }
        reversedTail.next = current.next;
        return result;
    }
}
