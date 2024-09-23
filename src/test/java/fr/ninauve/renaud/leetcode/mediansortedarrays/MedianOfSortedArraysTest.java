package fr.ninauve.renaud.leetcode.mediansortedarrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MedianOfSortedArraysTest {

    static Stream<Arguments> findMedianSortedArrays() {
        return Stream.of(
                Arguments.of(
                        new int[] {},
                        new int[] {1, 2, 3, 4, 5},
                        3d
                ),
                Arguments.of(
                        new int[] {},
                        new int[] {1, 2, 3, 4},
                        2.5d
                ),
                Arguments.of(
                        new int[] {1},
                        new int[] {3, 4, 5},
                        3.5d
                ),
                Arguments.of(
                        new int[] {1},
                        new int[] {3, 4, 5, 6},
                        4d
                ),
                Arguments.of(
                        new int[] {1, 3},
                        new int[] {4, 5},
                        3.5d
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void findMedianSortedArrays(int[] a, int[] b, double expected) {
        double actual = new MedianOfSortedArrays().findMedianSortedArrays(a, b);
        assertThat(actual).isEqualTo(expected);
    }
}