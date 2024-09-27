package fr.ninauve.renaud.leetcode.longestparentheses;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// https://leetcode.com/problems/longest-valid-parentheses/
public class LongestParentheses {
    private static final char OPEN = '(';
    private static final char CLOSE = ')';

    private LinkedList<Integer> open;
    private Map<Integer, Integer> validLengthByClose;
    private int max;

    public int longestValidParentheses(String s) {
        max = 0;
        open = new LinkedList<>();
        validLengthByClose = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == OPEN) {
                open.push(i);
            } else if (c == CLOSE) {
                handleClose(open, i);
            }
        }

        return max;
    }

    private void handleClose(LinkedList<Integer> open, int i) {
        boolean isInvalid = open.isEmpty();
        if (isInvalid) {
            open.clear();
        } else {
            handleValidClose(open, i);
        }
    }

    private void handleValidClose(LinkedList<Integer> open, int i) {
        int start = open.pop();
        final int currentLength = i - start + 1;
        final int validLength;
        if (start > 0) {
            int previousLength = validLengthByClose.getOrDefault(start - 1, 0);
            validLength = previousLength + currentLength;
        } else {
            validLength = currentLength;
        }
        validLengthByClose.put(i, validLength);
        max = Math.max(validLength, max);
    }
}
