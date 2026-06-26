package fr.ninauve.renaud.leetcode.zigzag;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ZigzagLinesConverterTest {

    @Test
    void should_convert_to_one_line() {
        String actual = ZigzagLinesConverter.toOneLineString("""
                P   A   H   N
                A P L S I I G
                Y   I   R
                """);

        assertThat(actual)
                .isEqualTo("PAHNAPLSIIGYIR");
    }
}