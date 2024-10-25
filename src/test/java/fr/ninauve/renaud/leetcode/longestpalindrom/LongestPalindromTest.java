package fr.ninauve.renaud.leetcode.longestpalindrom;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LongestPalindromTest {

    static Stream<Arguments> longestPalindrome() {
        return Stream.of(
                Arguments.of("a", "a"),
                Arguments.of("ab", "a"),
                Arguments.of("aba", "aba"),
                Arguments.of("zaba", "aba"),
                Arguments.of("zabbax", "abba"),
                Arguments.of("zabbaxazertyytrezatoto", "azertyytreza"),
                Arguments.of("zrezazertyytrezatoto", "azertyytreza")
        );
    }

    @ParameterizedTest
    @MethodSource
    void longestPalindrome(String str, String expected) {
        String actual = new LongestPalindrom().longestPalindrome(str);
        assertThat(actual).isEqualTo(expected);
    }
}