package fr.ninauve.renaud.leetcode.multiplystrings;

// https://leetcode.com/problems/multiply-strings
public class MultiplyStrings {

    public String multiply(String a, String b) {
        ListNode aHead = toListNode(a);
        ListNode bHead = toListNode(b);
        ListNode totalHead = null;
        ListNode currentA = aHead;
        ListNode currentB = bHead;
        ListNode currentTotal = null;

        while (currentB != null) {
            int currentVal = currentA.val * currentB.val;
            if (totalHead == null) {
                totalHead = new ListNode(currentVal);
                currentTotal = totalHead;
            } else {
                currentTotal.next = new ListNode(currentVal);
                currentTotal = currentTotal.next;
            }
            currentB = currentB.next;
        }

        return listNodeToString(totalHead);
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
