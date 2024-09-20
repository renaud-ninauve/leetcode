package fr.ninauve.renaud.leetcode.addtwonumbers;

// https://leetcode.com/problems/add-two-numbers
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode a, ListNode b) {
        ListNode currentA = a;
        ListNode currentB = b;
        ListNode resultHead = null;
        ListNode resultTail = null;

        do {
            if (resultHead == null) {
                resultHead = new ListNode(currentA.val + currentB.val);
                resultTail = resultHead;
            } else {
                resultTail.next = new ListNode(currentA.val + currentB.val);
                resultTail = resultTail.next;
            }
            currentA = currentA.next;
            currentB = currentB.next;
        } while(currentA != null || currentB != null);

        return resultHead;
    }
}
