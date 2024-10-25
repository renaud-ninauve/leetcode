package fr.ninauve.renaud.leetcode.stringtointeger;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StringToIntegerTest {

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "42;42",
            "-042;-42",
            "1337c0d3;1337",
            "0-1;0",
            "words and 987;0",
            "-2147483649;-2147483648",
            "2147483648;2147483647",
            "1234567890123456;2147483647"
    })
    void myAtoi(String s, int expected) {
        int actual = new StringToInteger().myAtoi(s);
        assertThat(actual).isEqualTo(expected);
    }
}