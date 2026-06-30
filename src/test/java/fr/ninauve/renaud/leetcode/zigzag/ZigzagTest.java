package fr.ninauve.renaud.leetcode.zigzag;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static fr.ninauve.renaud.leetcode.zigzag.ZigzagLinesConverter.toOneLineString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ZigzagTest {

    static Stream<Arguments> should_convert() {
        return Stream.of(
            Arguments.of("ABCDEF", 6, "ABCDEF"),
                Arguments.of(
                        "ABC",
                        2,
                        """
                                A C
                                B
                                """),
                Arguments.of(
                        "PAYPALISHIRING",
                        3,
                        """
                                P   A   H   N
                                A P L S I I G
                                Y   I   R
                                """),
                Arguments.of(
                        "PAYPALISHIRING",
                        4,
                        """
                                P     I    N
                                A   L S  I G
                                Y A   H R
                                P     I
                                """),
                Arguments.of("ABCDEF", 1, "ABCDEF"),
                Arguments.of(
                        "ABCD",
                        2,
                        """
                                A C
                                B D
                                """)
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_convert(String s, int numRows, String expectedZigzag) {
        String actual = new Zigzag().convert(s, numRows);

        String expected = toOneLineString(expectedZigzag);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("should_convert")
    void should_convert2(String s, int numRows, String expectedZigzag) {
        String actual = new Zigzag2().convert(s, numRows);

        String expected = toOneLineString(expectedZigzag);
        assertThat(actual).isEqualTo(expected);
    }
}