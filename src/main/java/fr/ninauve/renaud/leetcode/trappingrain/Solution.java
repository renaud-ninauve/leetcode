package fr.ninauve.renaud.leetcode.trappingrain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

// https://leetcode.com/problems/trapping-rain-water/
public class Solution {

    public int trap(int[] height) {
        List<Integer> sorted = Arrays.stream(height)
                .sorted()
                .distinct()
                .boxed()
                .toList();

        int waterTotal = 0;
        for (int i = 1; i < sorted.size(); i++) {
            int currentHeight = sorted.get(i);
            int previousHeight = sorted.get(i - 1);
            int trappedOne = trapAtHeight(height, currentHeight);
            int deltaHeight = currentHeight - previousHeight;
            waterTotal += trappedOne * deltaHeight;
        }
        return waterTotal;
    }

    private int trapAtHeight(int[] height, int currentHeight) {
        TrapAtHeightContext ctx = new TrapAtHeightContext(currentHeight);

        for (int columnHeight : height) {
            if (!ctx.left) {
                handleNothingAtLeft(ctx, columnHeight);
            } else {
                handleSomethingAtLeft(ctx, columnHeight);
            }
        }
        return ctx.trapped;
    }

    private void handleNothingAtLeft(TrapAtHeightContext ctx, int columnHeight) {
        if (columnHeight >= ctx.currentHeight) {
            ctx.left = true;
            ctx.emptyAfterLeft = 0;
        }
    }

    private void handleSomethingAtLeft(TrapAtHeightContext ctx, int columnHeight) {
        if (columnHeight >= ctx.currentHeight) {
            ctx.trapped += ctx.emptyAfterLeft;
            ctx.emptyAfterLeft = 0;
        } else {
            ctx.emptyAfterLeft++;
        }
    }

    private static class TrapAtHeightContext {
        private final int currentHeight;
        private boolean left = false;
        private int emptyAfterLeft = 0;
        private int trapped = 0;

        private TrapAtHeightContext(int currentHeight) {
            this.currentHeight = currentHeight;
        }
    }
}
