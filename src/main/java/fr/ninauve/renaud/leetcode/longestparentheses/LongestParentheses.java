package fr.ninauve.renaud.leetcode.longestparentheses;

import java.awt.desktop.AppReopenedEvent;
import java.util.LinkedList;

// https://leetcode.com/problems/longest-valid-parentheses/
public class LongestParentheses {
    private static final char OPEN = '(';
    private static final char CLOSE = ')';

    public int longestValidParentheses(String s) {
        CurrentRanges ctx = emptyCurrent();
        LinkedList<Integer> open = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == OPEN) {
                open.push(i);
            } else if (c == CLOSE) {
                ctx = handleClose(ctx, open, i);
            }
        }

        Range max = max(ctx.current, ctx.max);
        return max.length();
    }

    private CurrentRanges handleClose(CurrentRanges ctx, LinkedList<Integer> open, int i) {
        boolean isInvalid = open.isEmpty();
        if (isInvalid) {
            return new CurrentRangesBuilder()
                    .max(max(ctx.current, ctx.max))
                    .previous(Range.empty())
                    .current(Range.empty())
                    .build();
        } else {
            return handleValidClose(ctx, open, i);
        }
    }

    private CurrentRanges handleValidClose(CurrentRanges ctx, LinkedList<Integer> open, int i) {
        int start = open.pop();
        Range validRange = new Range(start, i);
        Range current = validRange.isJustAfter(ctx.previous) ? concat(ctx.previous, validRange) : validRange;
        Range previous = open.isEmpty() ? current : ctx.previous;
        return new CurrentRangesBuilder()
                .max(max(current, ctx.max))
                .current(current)
                .previous(previous)
                .build();
    }

    private static class CurrentRangesBuilder {
        private Range max;
        private Range previous;
        private Range current;

        private CurrentRangesBuilder max(Range max) {
            this.max = max;
            return this;
        }

        private CurrentRangesBuilder previous(Range previous) {
            this.previous = previous;
            return this;
        }

        private CurrentRangesBuilder current(Range current) {
            this.current = current;
            return this;
        }

        private CurrentRanges build() {
            return new CurrentRanges(max, previous, current);
        }
    }

    private static CurrentRanges emptyCurrent() {
        return new CurrentRanges(Range.empty(), Range.empty(), Range.empty());
    }

    private static class CurrentRanges {
        private final Range max;
        private final Range previous;
        private final Range current;

        private CurrentRanges(Range max, Range previous, Range current) {
            this.max = max;
            this.previous = previous;
            this.current = current;
        }
    }

    private static Range max(Range a, Range b) {
        return a.length() >= b.length() ? a : b;
    }

    private Range concat(Range a, Range b) {
        return new Range(a.start, b.end);
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

        private boolean isJustAfter(Range initial) {
            return length() > 0 && initial.length() > 0 && start == initial.end + 1;
        }
    }
}
