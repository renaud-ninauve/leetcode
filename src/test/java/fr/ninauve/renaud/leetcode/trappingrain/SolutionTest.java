package fr.ninauve.renaud.leetcode.trappingrain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void should_trap_nothing_when_no_heights() {
        int actual = new Solution().trap(new int[0]);
        assertThat(actual).isZero();
    }
}