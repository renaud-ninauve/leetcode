package fr.ninauve.renaud.leetcode.zigzag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Zigzag {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        if (numRows == 2) {
            return convertNumRows2(s);
        }

        List<StringBuilder> rows = createRows(numRows);
        final int diagWith = Math.max(1, numRows - 2);
        final int patternWidth = numRows + diagWith;
        final int maxDiagRow = numRows - 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int mod = i % patternWidth;
            if (mod + 1 < numRows) {
                rows.get(mod).append(c);
            } else {
                final int row;
                row = maxDiagRow - (mod + 1 - numRows);
                rows.get(row).append(c);
            }
        }
        return rows.stream()
                .map(StringBuilder::toString)
                .collect(Collectors.joining());
    }

    private String convertNumRows2(String s) {
        List<StringBuilder> rows = createRows(2);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int mod = i % 2;
            rows.get(mod).append(c);
        }
        return rows.stream()
                .map(StringBuilder::toString)
                .collect(Collectors.joining());
    }

    private List<StringBuilder> createRows(int numRows) {
        final List<StringBuilder> rows = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        return rows;
    }
}
