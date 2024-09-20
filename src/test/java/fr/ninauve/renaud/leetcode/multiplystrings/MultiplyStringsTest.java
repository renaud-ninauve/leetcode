package fr.ninauve.renaud.leetcode.multiplystrings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MultiplyStringsTest {

    @ParameterizedTest
    @CsvSource(delimiterString = "  ", value = {
            "3  4   12"
    })
    void multiply(String a, String b, String expected) {
        String actual = new MultiplyStrings().multiply(a, b);
        assertThat(actual).isEqualTo(expected);
    }
}