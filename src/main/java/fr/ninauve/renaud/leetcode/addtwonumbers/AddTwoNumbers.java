package fr.ninauve.renaud.leetcode.addtwonumbers;

// https://leetcode.com/problems/add-two-numbers
public class AddTwoNumbers {
    ListNode currentA = null;
    ListNode currentB = null;
    ListNode resultHead = null;
    ListNode resultTail = null;
    int carry = 0;

    public ListNode addTwoNumbers(ListNode a, ListNode b) {
        currentA = a;
        currentB = b;
        resultHead = null;
        resultTail = null;
        carry = 0;

        do {
            if (resultHead == null) {
                resultHead = addCurrentVals();
                resultTail = resultHead;
            } else {
                resultTail.next = addCurrentVals();
                resultTail = resultTail.next;
            }
            currentA = currentA.next;
            currentB = currentB.next;
        } while(currentA != null || currentB != null);

        if (carry > 0) {
            resultTail.next = new ListNode(carry);
        }
        return resultHead;
    }

    private ListNode addCurrentVals() {
        int sum = currentA.val + currentB.val + carry;
        carry = sum / 10;
        return new ListNode(sum % 10);
    }
}
