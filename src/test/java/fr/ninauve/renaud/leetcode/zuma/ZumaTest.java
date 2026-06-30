package fr.ninauve.renaud.leetcode.zuma;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ZumaTest {

    @ParameterizedTest
    @CsvSource(value = {
            "WRRBBW RB -1",
            "WWRRBBWW WRBRW 2",
            "G GGGGG 2"
    })
    void should_findMinStep(String board, String hand, int expected) {
        int actual = new Zuma().findMinStep(board, hand);
        assertThat(actual).isEqualTo(expected);
    }
}