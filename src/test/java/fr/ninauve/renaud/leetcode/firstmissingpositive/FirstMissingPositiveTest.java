package fr.ninauve.renaud.leetcode.firstmissingpositive;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FirstMissingPositiveTest {

    @ParameterizedTest
    @CsvSource(delimiterString = " => ", value = {
            "1,2,0 => 3",
            "3,4,-1,1 => 2",
            "7,8,9,11,12 => 1",
            "-4,-3,-4,-2 => 1",
            "100000,3,4000,2,15,1,99999 => 4"
    })
    void test(String values, int expected) {
        int[] nums = Arrays.stream(values.split(",")).mapToInt(Integer::parseInt).toArray();
        int actual = new FirstMissingPositive().firstMissingPositive(nums);
        assertThat(actual).isEqualTo(expected);
    }
}