package fr.ninauve.renaud.leetcode.firstmissingpositive;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @ParameterizedTest
    @CsvSource(delimiterString = " => ", value ={
            "1,2,0 => 2"})
    void test(String values, int expected) {
        int[] nums = Arrays.stream(values.split(",")).mapToInt(Integer::parseInt).toArray();
        int actual = new Solution().firstMissingPositive(nums);
        assertThat(actual).isEqualTo(expected);
    }
}