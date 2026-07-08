package fr.ninauve.renaud.leetcode.problems.zigzag;

import java.util.ArrayList;
import java.util.List;

public class Zigzag2 {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        final int lastRow = numRows - 1;
        final int patternWidth = numRows * 2 - 2;
        final StringBuilder result = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            boolean hasNext = true;
            int patternStart = 0;
            while (hasNext) {
                final List<Integer> withinPatternIndexes = new ArrayList<>();
                withinPatternIndexes.add(row);
                if (row != 0 && row != lastRow) {
                    withinPatternIndexes.add(patternWidth - row);
                }
                for (int withinPatternIndex : withinPatternIndexes) {
                    int index = patternStart + withinPatternIndex;
                    if (index >= s.length()) {
                        hasNext = false;
                        break;
                    }
                    result.append(s.charAt(index));
                }
                patternStart += patternWidth;
            }
        }
        return result.toString();
    }
}
