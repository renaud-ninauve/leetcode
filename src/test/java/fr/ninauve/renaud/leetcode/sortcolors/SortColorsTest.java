package fr.ninauve.renaud.leetcode.sortcolors;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SortColorsTest {

    static Stream<Arguments> sortColors() {
        return Stream.of(
                Arguments.of(new int[]{2, 0, 2, 1, 1, 0}, new int[]{0, 0, 1, 1, 2, 2}),
                Arguments.of(new int[]{2, 0, 1}, new int[]{0, 1, 2})
        );
    }

    @ParameterizedTest
    @MethodSource
    void sortColors(int[] colors, int[] expected) {
        new SortColors().sortColors(colors);
        assertThat(colors).isEqualTo(expected);
    }
}