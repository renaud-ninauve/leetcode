package fr.ninauve.renaud.leetcode.problems.divide2integers;

import fr.ninauve.renaud.leetcode.problems.divide2integers.Divide2Integers.Unsigned32;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
            "2147483647;2;1073741823",
            "2147483647;3;715827882"
    })
    void divide(int dividend, int divisor, int expected) {
        int actual = new Divide2Integers().divide(dividend, divisor);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "0001;0001;0010",
            "0001;0011;0100",
            "0011;0011;0110",
            "+1111111111111111111111111111111;1;-10000000000000000000000000000000",
            "+1111111111111111111111111111111;10;-10000000000000000000000000000000",
            "+1111111111111111111111111111100;100;-10000000000000000000000000000000"
    })
    void addBinary(String a, String b, String expected) {
        Unsigned32 a32 = new Unsigned32(Integer.parseInt(a, 2));
        Unsigned32 b32 = new Unsigned32(Integer.parseInt(b, 2));
        Unsigned32 actual = a32.add(b32);
        assertThat(actual).isEqualTo(new Unsigned32(Integer.parseInt(expected, 2)));
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "123;321;444",
            "2147483647;1;-2147483648",
            "2147483647;2;-2147483648",
            "-2147483648;0;-2147483648"
    })
    void add(int a, int b, int expected) {
        Unsigned32 a32 = new Unsigned32(a);
        Unsigned32 b32 = new Unsigned32(b);
        Unsigned32 actual = a32.add(b32);
        assertThat(actual).isEqualTo(new Unsigned32(expected));
    }
}