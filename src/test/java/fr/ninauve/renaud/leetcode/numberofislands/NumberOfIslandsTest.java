package fr.ninauve.renaud.leetcode.numberofislands;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NumberOfIslandsTest {

    static Stream<Arguments> should_count_islands() {
        final String filledSquare = """
                11111111
                11111111
                11111111
                11111111
                """;
        final String emptySquare = """
                11111111
                1......1
                1......1
                11111111
                """;
        final String dots = """
                1.1.1.1.
                .1.1.1.1
                1.1.1.1.
                .1.1.1.1
                """;
        return Stream.of(
                Arguments.of("filled square", fourIslands(filledSquare), 4),
                Arguments.of("empty square", fourIslands(emptySquare), 4),
                Arguments.of("dots", fourIslands(dots), 64));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource
    void should_count_islands(String scenario, char[][] grid, int expected) {
        int actual = new NumberOfIslands().numIslands(grid);
        assertThat(actual).isEqualTo(expected);
    }

    static char[][] fourIslands(String pattern) {
        String surroundedByBlankLeftRight = pattern.lines()
                .map(l -> " " + l + " ")
                .collect(Collectors.joining(lineSeparator()));
        String surroundedByBlanks =
                lineSeparator() + surroundedByBlankLeftRight + lineSeparator();
        String duplicateH = surroundedByBlanks.lines()
                .map(l -> l + l)
                .collect(Collectors.joining(lineSeparator()));
        String duplicateV = duplicateH + lineSeparator() + duplicateH + lineSeparator() + lineSeparator();
        List<String> lines = duplicateV.lines().toList();
        int nbRows = lines.size();
        int nbCols = lines.get(1).length();
        char[][] grid = new char[nbRows][nbCols];
        for (int row = 0; row < nbRows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < nbCols; col++) {
                if (col >= line.length()) {
                    grid[row][col] = '0';
                    continue;
                }
                char currentChar = line.charAt(col);
                if (currentChar == '1') {
                    grid[row][col] = '1';
                } else {
                    grid[row][col] = '0';
                }

            }
        }
        return grid;
    }
}