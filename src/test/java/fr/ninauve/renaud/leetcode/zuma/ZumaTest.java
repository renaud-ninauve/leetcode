package fr.ninauve.renaud.leetcode.zuma;

import fr.ninauve.renaud.leetcode.zuma.Zuma.Group;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NavigableSet;

import static org.assertj.core.api.Assertions.assertThat;

class ZumaTest {

    @ParameterizedTest
    @CsvSource(delimiter = ' ', value = {
            "WRRB WG -1",
            "WRRBBW RB -1",
            "WWRRBBWW WRBRW 2",
            "G GGGGG 2"
    })
    void should_findMinStep(String board, String hand, int expected) {
        int actual = new Zuma().findMinStep(board, hand);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(delimiterString = " => ", value = {
            "R => R",
            "RR => RR",
            "RRR => ",
            "RRRWWW => ",
            "RRRBWWW => B",
            "BRRRB => BB",
            "BRRRBB => ",
    })
    void should_deleteTris(String board, String expectedParam) {
        final String expected = expectedParam != null ? expectedParam : "";
        String actual = new Zuma().deleteTris(board);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_convert_to_groups() {
        NavigableSet<Group> actual = new Zuma().toGroups("BGGB");
        assertThat(actual)
                .containsExactly(
                        new Group('B', 0, 1),
                        new Group('G', 1, 2),
                        new Group('B', 3, 1)
                );
    }

    @Test
    void should_convert_to_groups2() {
        NavigableSet<Group> actual = new Zuma().toGroups("BGGBB");
        assertThat(actual)
                .containsExactly(
                        new Group('B', 0, 1),
                        new Group('G', 1, 2),
                        new Group('B', 3, 2)
                );
    }
}