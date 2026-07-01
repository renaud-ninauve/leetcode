package fr.ninauve.renaud.leetcode.romantointeger;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RomanToIntegerTest {

    @ParameterizedTest
    @CsvSource(delimiterString = " => ", value = {
            "I => 1",
            "V => 5",
            "VI => 6",
            "X => 10",
            "L => 50",
            "C => 100",
            "D => 500",
            "M => 1000",
            "II => 2",
            "III => 3",
            "XX => 20",
            "CC => 200",
            "MM => 2000",
            //
            "IV => 4"
    })
    void should_convert_to_arabic(String romanNumber, int expected) {
        int actual = new RomanToInteger().romanToInt(romanNumber);
        assertThat(actual).isEqualTo(expected);
    }
}