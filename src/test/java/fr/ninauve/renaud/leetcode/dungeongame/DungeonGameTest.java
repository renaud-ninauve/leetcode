package fr.ninauve.renaud.leetcode.dungeongame;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DungeonGameTest {

    static Stream<Arguments> should_return_min_health() {
        return Stream.of(
                Arguments.of(
                        dungeon("""
                                -2 -3 3
                                -5 -10 1
                                10 30 -5
                                """),
                        7),
                Arguments.of(
                        dungeon("""
                                0
                                """),
                        1),
                Arguments.of(
                        dungeon("""
                                 3  0 -3
                                -3 -2 -2
                                 3  1 -3
                                """),
                        1),
                Arguments.of(
                        dungeon("""
                                 0  0  0
                                -1  0  0
                                 2  0 -2
                                """),
                        2)
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_return_min_health(int[][] dungeon, int expected) {
        int actual = new DungeonGame().calculateMinimumHP(dungeon);
        assertThat(actual).isEqualTo(expected);
    }

    static int[][] dungeon(String dungeonStr) {
        return dungeonStr
                .lines()
                .map(DungeonGameTest::toInts)
                .toArray(int[][]::new);
    }

    static int[] toInts(String str) {
        List<Integer> ints = new ArrayList<>();
        Scanner scanner = new Scanner(str);
        while (scanner.hasNextInt()) {
            ints.add(scanner.nextInt());
        }
        return ints.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}