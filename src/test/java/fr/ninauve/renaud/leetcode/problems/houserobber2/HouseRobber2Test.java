package fr.ninauve.renaud.leetcode.problems.houserobber2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class HouseRobber2Test {
    @ParameterizedTest
    @CsvSource(delimiterString = " => ", value = {
            "[2,3,2] => 3",
            "[1,2,3,1] => 4",
            "[1,2,3] => 3",
            "[1,2,3,4,5] => 8",
            "[5,4,3,2,1] => 8",
            "[100,1,1,1,1,100] => 102",
            "[1,100,1,1,1,100] => 201"
    })
    void should_rob(String numsParam, int expected) {
        int[] nums = Arrays.stream(numsParam.substring(1, numsParam.length() - 1).split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        int actual = new HouseRobber2().rob(nums);

        assertThat(actual)
                .isEqualTo(expected);
    }
}