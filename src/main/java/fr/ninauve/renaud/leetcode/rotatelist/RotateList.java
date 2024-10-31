package fr.ninauve.renaud.leetcode.rotatelist;

// https://leetcode.com/problems/rotate-list/
public class RotateList {
    ListNode head;
    ListNode reversed;
    int length;
    ListNode result;
    ListNode resultTail;

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        this.head = head;
        createReverseList();
        int shift = k % length;
        if (shift == 0) {
            return head;
        }
        prepend(shift);
        append(length - shift);
        return result;
    }

    void createReverseList() {
        reversed = new ListNode(head.val);
        length = 1;
        ListNode current = head;
        while ((current = current.next) != null) {
            reversed = new ListNode(current.val, reversed);
            length++;
        }
    }

    void prepend(int nbElement) {
        result = new ListNode(reversed.val);
        resultTail = result;
        ListNode current = reversed;
        for (int i = 0; i < nbElement - 1; i++) {
            current = current.next;
            result = new ListNode(current.val, result);
        }
    }

    void append(int nbElement) {
        ListNode current = head;
        ListNode tail = resultTail;
        for (int i = 0; i < nbElement; i++) {
            tail.next = new ListNode(current.val);
            tail = tail.next;
            current = current.next;
        }
    }
}
