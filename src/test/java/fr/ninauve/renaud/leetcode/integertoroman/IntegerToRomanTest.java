package fr.ninauve.renaud.leetcode.integertoroman;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IntegerToRomanTest {
    @ParameterizedTest
    @CsvSource(delimiterString = " => ", value = {
            "1 => I",
            "5 => V",
            "10 => X",
            "50 => L",
            "100 => C",
            "500 => D",
            "1000 => M",
            //
            "2 => II",
            "3 => III",
            "4 => IV",
            "6 => VI",
            "7 => VII",
            "8 => VIII",
            "9 => IX",
            "11 => XI",
            "20 => XX",
            "30 => XXX",
            "40 => XL",
            "3421 => MMMCDXXI"
    })
    void should_convert_to_roman(int arabicNumber, String expectedRomanNumber) {
        String romanNumber = new IntegerToRoman().intToRoman(arabicNumber);
        assertThat(romanNumber).isEqualTo(expectedRomanNumber);
    }
}