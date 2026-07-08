package fr.ninauve.renaud.leetcode.problems.zigzag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Zigzag {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> rows = createRows(numRows);
        int col = numRows - 1;
        int row = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (col == 0 && row < numRows-1) {
                row++;
            } else if (col < numRows - 2){
                col++;
                row--;
            } else {
                col = 0;
                row = 0;
            }
            rows.get(row).append(c);
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
