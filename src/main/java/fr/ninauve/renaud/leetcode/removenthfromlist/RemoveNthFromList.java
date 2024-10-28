package fr.ninauve.renaud.leetcode.removenthfromlist;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/remove-nth-node-from-end-of-list/
public class RemoveNthFromList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> listNodes = listNodes(head);
        if (n > listNodes.size()) {
            return head;
        }
        if (n == listNodes.size()) {
            return head.next;
        }
        int removeIndex = listNodes.size() - n;
        ListNode remove = listNodes.get(removeIndex);
        ListNode removeParent = listNodes.get(removeIndex - 1);
        removeParent.next = remove.next;
        return head;
    }

    private List<ListNode> listNodes(ListNode head) {
        final List<ListNode> listNodes = new ArrayList<>();
        ListNode current = head;
        while(current != null) {
            listNodes.add(current);
            current = current.next;
        }
        return listNodes;
    }
}
