package fr.ninauve.renaud.leetcode.trappingrain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TrappingRainTest {

    static Stream<Arguments> should_trap_water() {
        return Stream.of(Arguments.of(
                        """
                                ..........
                                ..........
                                ..........
                                ..........
                                """,
                        0
                ),
                Arguments.of(
                        """
                                1234567890
                                ..........
                                #........#
                                #........#
                                ##########
                                """,
                        16
                ),
                Arguments.of(
                        """
                                1234567890
                                ....#.....
                                #...#....#
                                #...#....#
                                ##########
                                """,
                        14
                ), Arguments.of(
                        """
                                123456789012
                                ............
                                .......#....
                                ...#...##.#.
                                .#.##.######
                                """,
                        6
                ),
                Arguments.of(
                        """
                                123456
                                .....#
                                #....#
                                #..#.#
                                ##.###
                                ##.###
                                """,
                        9
                ));
    }

    @ParameterizedTest
    @MethodSource
    void should_trap_water(String heightsString, int expected) {
        int[] heights = parseHeights(heightsString);

        int actual = new TrappingRain().trap(heights);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void parse_heights() {
        int[] actual = parseHeights("""
                1234567890
                ..........
                .......#..
                ....#..#..
                .#.######.
                """
        );

        assertThat(actual).containsExactly(0, 1, 0, 1, 2, 1, 1, 3, 1, 0);
    }

    private int[] parseHeights(String heightsString) {
        List<Integer> heightsList = heightsString.lines()
                .map(l -> l.chars().mapToObj(c -> c == '#' ? 1 : 0).toList())
                .reduce(List.of(), (a, b) -> {
                    if (a.isEmpty()) {
                        return b;
                    }
                    final List<Integer> sum = new ArrayList<>(a.size());
                    for (int i = 0; i < a.size(); i++) {
                        sum.add(a.get(i) + b.get(i));
                    }
                    return sum;
                });
        return heightsList.stream().mapToInt(i -> i).toArray();
    }
}