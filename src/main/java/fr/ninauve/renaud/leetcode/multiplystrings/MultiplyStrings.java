package fr.ninauve.renaud.leetcode.multiplystrings;

// https://leetcode.com/problems/multiply-strings
public class MultiplyStrings {

    public String multiply(String a, String b) {
        ListNode aHead = toListNode(a);
        ListNode bHead = toListNode(b);

        ListNode total = multiply(aHead.val, bHead);

        return listNodeToString(total);
    }

    private ListNode multiply(int a, ListNode b) {
        ListNode totalHead = null;
        ListNode current = b;
        ListNode currentTotal = null;
        int carry = 0;
        while (current != null) {
            int multidigitResult = a * current.val + carry;
            int currentVal = multidigitResult % 10;
            carry = multidigitResult / 10;
            if (totalHead == null) {
                totalHead = new ListNode(currentVal);
                currentTotal = totalHead;
            } else {
                currentTotal.next = new ListNode(currentVal);
                currentTotal = currentTotal.next;
            }
            current = current.next;
        }
        if (carry > 0) {
            currentTotal.next = new ListNode(carry);
        }
        return totalHead;
    }

    public ListNode toListNode(String number) {
        return number.chars()
                .mapToObj(Character::toString)
                .map(Integer::parseInt)
                .map(ListNode::new)
                .reduce((a, b) -> {
                    b.next = a;
                    return b;
                }).orElseThrow();
    }

    public String listNodeToString(ListNode listNode) {
        final StringBuilder sb = new StringBuilder();
        append(sb, listNode);
        return sb.toString();
    }

    private void append(StringBuilder sb, ListNode listNode) {
        if (listNode.next == null) {
            sb.append(listNode.val);
            return;
        }
        append(sb, listNode.next);
        sb.append(listNode.val);
    }
}
