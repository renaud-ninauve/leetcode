package fr.ninauve.renaud.leetcode.addtwonumbers;

// https://leetcode.com/problems/add-two-numbers
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode a, ListNode b) {
        return new ListNode(a.val + b.val);
    }
}
