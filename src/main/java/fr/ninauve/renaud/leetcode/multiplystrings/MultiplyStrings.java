package fr.ninauve.renaud.leetcode.multiplystrings;

// https://leetcode.com/problems/multiply-strings
public class MultiplyStrings {
    public String multiply(String a, String b) {
        ListNode aHead = toListNode(a);
        ListNode bHead = toListNode(b);
        ListNode total = new ListNode(0);
        ListNode currentA = aHead;
        int pow = 0;
        while (currentA != null) {
            ListNode currentTotal = multiply(currentA.val, bHead);
            ListNode currentTotalPow = pow10(currentTotal, pow);
            total = new AddTwoNumbers().addTwoNumbers(total, currentTotalPow);

            currentA = currentA.next;
            pow++;
        }

        return listNodeToString(total);
    }

    private ListNode pow10(ListNode listNode, int pow) {
        if (zero().equals(listNode)) {
            return zero();
        }
        ListNode result = listNode;
        for (int i = 0; i < pow; i++) {
            result = new ListNode(0, result);
        }
        return result;
    }

    private ListNode multiply(int a, ListNode b) {
        if (a == 0 || zero().equals(b)) {
            return zero();
        }
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

    public static class AddTwoNumbers {
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
                currentA = next(currentA);
                currentB = next(currentB);
            } while (currentA != null || currentB != null);

            if (carry > 0) {
                resultTail.next = new ListNode(carry);
            }
            return resultHead;
        }

        private ListNode addCurrentVals() {
            int a = getVal(currentA);
            int b = getVal(currentB);
            int sum = a + b + carry;
            carry = sum / 10;
            return new ListNode(sum % 10);
        }

        private int getVal(ListNode listNode) {
            return listNode == null ? 0 : listNode.val;
        }

        private ListNode next(ListNode listNode) {
            return listNode == null ? null : listNode.next;
        }
    }

    private static ListNode zero() {
        return new ListNode(0, null);
    }
}
