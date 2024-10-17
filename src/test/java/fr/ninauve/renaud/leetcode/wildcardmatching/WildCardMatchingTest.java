package fr.ninauve.renaud.leetcode.wildcardmatching;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WildCardMatchingTest {

    static Stream<Arguments> isMatch() {
        return Stream.of(
                arguments()
                        .string("")
                        .pattern("")
                        .expected(true),
                arguments()
                        .string("toto")
                        .pattern("")
                        .expected(false),
                arguments()
                        .string("toto")
                        .pattern("toto")
                        .expected(true),
                arguments()
                        .string("toto")
                        .pattern("xxxx")
                        .expected(false),
                arguments()
                        .string("toto")
                        .pattern("tot?")
                        .expected(true),
                arguments()
                        .string("toto")
                        .pattern("*")
                        .expected(true),
                arguments()
                        .string("")
                        .pattern("*")
                        .expected(true),
                arguments()
                        .string("toto")
                        .pattern("*")
                        .expected(true),
                arguments()
                        .string("toto")
                        .pattern("toto*")
                        .expected(true),
                arguments()
                        .string("toto")
                        .pattern("to*to")
                        .expected(true),
                arguments()
                        .string("to")
                        .pattern("to*to")
                        .expected(false),
                arguments()
                        .string("tototoaa")
                        .pattern("to*to")
                        .expected(false),
                arguments()
                        .string("toxxto")
                        .pattern("to*to")
                        .expected(true),
                arguments()
                        .string("toaabbaabbccdd")
                        .pattern("to*aabbccdd")
                        .expected(true),

                // leetcode
                arguments()
                        .string("aaaa")
                        .pattern("***a")
                        .expected(true),
                arguments()
                        .string("adceb")
                        .pattern("*a*b")
                        .expected(true),
                arguments()
                        .string("aa")
                        .pattern("a")
                        .expected(false),
                arguments()
                        .string("abc")
                        .pattern("*ab")
                        .expected(false),
                arguments()
                        .string("abb")
                        .pattern("**??")
                        .expected(true)
        );
    }

    @ParameterizedTest
    @MethodSource
    void isMatch(final String str, final String pattern, final boolean expected) {
        boolean actual = new WildCardMatching().isMatch(str, pattern);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> matchSubPattern() {
        return Stream.of(
                Arguments.of(
                        "abcd", "abcd", WildCardMatching.MatchResult.found(0, 3)
                ),Arguments.of(
                        "abcd", "a??d", WildCardMatching.MatchResult.found(0, 3)
                ), Arguments.of(
                        "xxabcd", "abcd", WildCardMatching.MatchResult.found(2, 5)
                ), Arguments.of(
                        "xxabcdxx", "abcd", WildCardMatching.MatchResult.found(2, 5)
                ), Arguments.of(
                        "xxababcd", "abcd", WildCardMatching.MatchResult.found(4, 7)
                ), Arguments.of(
                        "xxabxx", "abcd", WildCardMatching.MatchResult.notFound()
                ), Arguments.of(
                        "xxxx", "abcdefghijklmnopqrstuvwxyz", WildCardMatching.MatchResult.notFound()
                ), Arguments.of(
                        "", "abcd", WildCardMatching.MatchResult.notFound()
                ), Arguments.of(
                        "aaaa", "a", WildCardMatching.MatchResult.found(0, 0)
                ));
    }

    @ParameterizedTest
    @MethodSource
    void matchSubPattern(final String str, final String pattern, final WildCardMatching.MatchResult expected) {
        WildCardMatching.MatchResult actual = new WildCardMatching().matchSubPattern(str, pattern);
        assertThat(actual.start).isEqualTo(expected.start);
        assertThat(actual.end).isEqualTo(expected.end);
        assertThat(actual.matches).isEqualTo(expected.matches);
    }

    private static ArgumentsBuilder arguments() {
        return new ArgumentsBuilder();
    }

    private static class ArgumentsBuilder {
        private String string;
        private String pattern;
        private boolean expected;

        private ArgumentsBuilder string(String string) {
            this.string = string;
            return this;
        }

        private ArgumentsBuilder pattern(String pattern) {
            this.pattern = pattern;
            return this;
        }

        private Arguments expected(boolean expected) {
            this.expected = expected;
            return build();
        }

        private Arguments build() {
            return Arguments.of(string, pattern, expected);
        }
    }
}