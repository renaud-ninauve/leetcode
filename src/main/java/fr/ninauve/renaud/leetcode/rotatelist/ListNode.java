package fr.ninauve.renaud.leetcode.rotatelist;

import java.util.List;
import java.util.Objects;

public class ListNode {

    public static ListNode listNode(List<Integer> values) {
        if (values.isEmpty()) {
            return null;
        }
        Integer value = values.get(0);
        if (values.size() == 1) {
            return new ListNode(value);
        }
        return new ListNode(value, listNode(values.subList(1, values.size())));
    }

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListNode listNode)) return false;
        return val == listNode.val && Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        toString(sb);
        sb.append("]");
        return sb.toString();
    }

    private void toString(final StringBuilder sb) {
        sb.append(val);
        if (next == null) {
            return;
        }
        sb.append(", ");
        next.toString(sb);
    }
}
