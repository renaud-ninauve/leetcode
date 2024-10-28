package fr.ninauve.renaud.leetcode.divide2integers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Divide2IntegersTest {

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "0;12345;0",
            "42;1;42",
            "42;42;1",
            "42;-1;-42",
            "-42;1;-42",
            "-42;-1;42",
            "42;-42;-1",
            "-42;42;-1",
            "-42;-42;1",
            "42;2;21",
            "42;-2;-21",
            "-42;2;-21",
            "-42;-2;21",
            "-2147483648;-1;2147483647",
            "10;3;3",
            "1000001;4;250000",
            "1;2;0",
            "2147483647;2;1"
    })
    void divide(int dividend, int divisor, int expected) {
        int actual = new Divide2Integers().divide(dividend, divisor);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> nextDividend() {
        return Stream.of(
                Arguments.of(255, 8, 5, new Divide2Integers.BinarySlice(15, 2, 5)),
                Arguments.of(255, 8, 2, new Divide2Integers.BinarySlice(0, 0, 2)),
                Arguments.of(255, 8, 3, new Divide2Integers.BinarySlice(15, 0, 3)),
                Arguments.of(255, 8, 30, new Divide2Integers.BinarySlice(15, 4, 30)),
                Arguments.of(255, 1, 0, new Divide2Integers.BinarySlice(1, 0, 0)));
    }

    @ParameterizedTest
    @MethodSource
    void nextDividend(int dividend, int divisor, int highestPow, Divide2Integers.BinarySlice expected) {
        Divide2Integers.BinarySlice actual = new Divide2Integers().nextDividend(dividend, divisor, highestPow);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "0;30;0",
            "1;30;0",
            "1;0;1",
            "256;8;1",
            "12;0;0",
            "12;1;0",
            "12;2;1",
            "12;3;1",
            "12;4;0"
    })
    void binaryAtTest(int value, int pow, int expected) {
        int actual = new Divide2Integers().binaryAt(value, pow);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "0;0;30;0",
            "1;1;30;0",
            "1;0;30;1",
            "12;0;3;12",
            "12;0;4;12",
            "12;1;2;2",
            "255;30;30;0"
    })
    void binaryAtTest2(int value, int start, int end, int expected) {
        int actual = new Divide2Integers().binaryAt(value, start, end);
        assertThat(actual).isEqualTo(expected);
    }
}