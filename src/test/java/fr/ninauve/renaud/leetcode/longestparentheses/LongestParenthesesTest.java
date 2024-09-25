package fr.ninauve.renaud.leetcode.longestparentheses;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LongestParenthesesTest {

    static Stream<Arguments> longestValidParentheses() {
        return Stream.of(
                Arguments.of(
                        "",
                        0
                ),
                Arguments.of(
                        "()",
                        2
                ),
                Arguments.of(
                        ")",
                        0
                ),
                Arguments.of(
                        ")()",
                        2
                ),
                Arguments.of(
                        "())",
                        2
                ),
                Arguments.of(
                        "()()",
                        4
                ));
    }

    @ParameterizedTest
    @MethodSource
    void longestValidParentheses(String str, int expected) {
        int actual = new LongestParentheses().longestValidParentheses(str);
        assertThat(actual).isEqualTo(expected);
    }
}