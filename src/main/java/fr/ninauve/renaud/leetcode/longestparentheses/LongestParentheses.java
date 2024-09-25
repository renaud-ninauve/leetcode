package fr.ninauve.renaud.leetcode.longestparentheses;

import java.util.LinkedList;

// https://leetcode.com/problems/longest-valid-parentheses/
public class LongestParentheses {
    private static final char OPEN = '(';
    private static final char CLOSE = ')';

    public int longestValidParentheses(String s) {
        Range max = Range.empty();
        Range current = Range.empty();
        LinkedList<Integer> open = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == OPEN) {
                open.add(i);
            } else if (c == CLOSE) {
                if (open.isEmpty()) {
                    if (current.length() > max.length()) {
                        max = current;
                    }
                } else {
                    int start = open.pop();
                    current = new Range(start, i);
                }
            }
        }
        if (current.length() > max.length()) {
            max = current;
        }
        return max.length();
    }

    private static class Range {
        private final int start;
        private final int end;

        private static Range empty() {
            return new Range(-1, -1);
        }

        private Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        private int length() {
            return start < 0 ? 0 : end - start + 1;
        }
    }
}
