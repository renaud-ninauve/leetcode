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

    @Test
    void should_trap_nothing_when_heights_are_zeros() {
        int actual = new Solution().trap(new int[] {0, 0, 0});
        assertThat(actual).isZero();
    }

    @Test
    void should_trap_in_height_0_when_enclosed_by_height_one() {
        int actual = new Solution().trap(new int[] {0, 1, 0, 1, 0, 0, 1, 0});
        assertThat(actual).isEqualTo(3);
    }

    @Test
    void should_trap_when_several_heights1() {
        int actual = new Solution().trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1});
        assertThat(actual).isEqualTo(6);
    }

    @Test
    void should_trap_when_several_heights2() {
        int actual = new Solution().trap(new int[] {4,2,0,3,2,5});
        assertThat(actual).isEqualTo(9);
    }
}