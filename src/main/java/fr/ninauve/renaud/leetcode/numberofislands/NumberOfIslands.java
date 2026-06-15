package fr.ninauve.renaud.leetcode.numberofislands;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberOfIslands {
    int nbIslands = 0;
    int maxIslandsIndex = 0;

    public int numIslands(char[][] grid) {
        Map<Col, Integer> previousRow = new HashMap<>();
        for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
            int nbCols = grid[rowIndex].length;
            Map<Col, Integer> currentRow = new HashMap<>();
            for (int colIndex = 0; colIndex < nbCols; colIndex++) {
                Col col = col(colIndex);
                char value = grid[rowIndex][colIndex];
                if (value == '0') {
                    continue;
                }
                final int leftValue = colIndex > 0 ? currentRow.getOrDefault(col.left(), 0) : 0;
                final int upValue = rowIndex > 0 ? previousRow.getOrDefault(col, 0) : 0;
                if (leftValue == 0 && upValue == 0) {
                    nbIslands++;
                    maxIslandsIndex++;
                    currentRow.put(col, maxIslandsIndex);
                } else if (leftValue != upValue && leftValue != 0 && upValue != 0){
                    currentRow.put(col, upValue);
                    currentRow = replaceValues(currentRow, leftValue, upValue);
                    previousRow = replaceValues(previousRow, leftValue, upValue);
                    nbIslands--;
                } else if (leftValue != 0) {
                    currentRow.put(col, leftValue);
                } else {
                    currentRow.put(col, upValue);
                }
            }
            previousRow = currentRow;
        }
        return nbIslands;
    }

    static Map<Col, Integer> replaceValues(Map<Col, Integer> values, int oldValue, int newValue) {
        return values.entrySet()
                .stream()
                .map(e -> e.getValue() == oldValue ? new AbstractMap.SimpleEntry<>(e.getKey(), newValue) : e)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    static Col col(int value) {
        return new Col(value);
    }
    record Col(int value){
        Col {
            if (value < 0) {
                throw new IllegalArgumentException();
            }
        }
        Col left() {
            return new Col(value - 1);
        }
    }
}
