package fr.ninauve.renaud.leetcode.wildcardmatching;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
                        .expected(false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void isMatch(final String str, final String pattern, final boolean expected) {
        boolean actual = new WildCardMatching().isMatch(str, pattern);
        assertThat(actual).isEqualTo(expected);
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