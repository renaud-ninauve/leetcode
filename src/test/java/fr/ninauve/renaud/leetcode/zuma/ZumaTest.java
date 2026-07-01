package fr.ninauve.renaud.leetcode.zuma;

import fr.ninauve.renaud.leetcode.zuma.Zuma.ColoredBall;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static fr.ninauve.renaud.leetcode.zuma.Zuma.deleteBiggerThanTris;
import static fr.ninauve.renaud.leetcode.zuma.Zuma.toBoard;
import static org.assertj.core.api.Assertions.assertThat;

class ZumaTest {

    @ParameterizedTest
    @CsvSource(delimiter = ' ', value = {
            "WRRB WG -1",
            "WRRBBW RB -1",
            "WWRRBBWW WRBRW 2",
            "G GGGGG 2",
            "RRWWRRBBRR WB 2",
            "BGGRRYY BBYRG 5",
            "RRYGGYYRRYYGGYRR GGBBB 5"
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
            "RRRR => ",
            "RRRWWW => ",
            "RRRBWWW => B",
            "BRRRB => BB",
            "BRRRBB => ",
    })
    void should_deleteBiggerThanTris(String boardParam, String expectedParam) {
        final List<ColoredBall> actual = toBalls(boardParam);
        final List<ColoredBall> expected = toBalls(expectedParam);

        deleteBiggerThanTris(actual);
        assertThat(actual).containsExactlyElementsOf(expected);
    }

    static Stream<Arguments> should_insert() {
        return Stream.of(
                Arguments.of(
                        toBoard("RR"), 0, new ColoredBall('B'), toBoard("BRR"),
                        toBoard("RR"), 1, new ColoredBall('B'), toBoard("RBR"),
                        toBoard("RR"), 2, new ColoredBall('B'), toBoard("RRB"),
                        toBoard("RR"), 0, new ColoredBall('R'), toBoard(""),
                        toBoard("RRBBR"), 2, new ColoredBall('B'), toBoard(""),
                        toBoard("RRWWRRBBRR"), 2, new ColoredBall('W'), toBoard("BBRR"),
                        toBoard("RRWWRRBBRR"), 2, new ColoredBall('R'), toBoard("WWRRBBRR"),
                        toBoard("RRWWRRBBRR"), 6, new ColoredBall('B'), toBoard("RRWW")
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_insert(Zuma.Board board, int index, ColoredBall coloredBall, Zuma.Board expected) {
        Zuma.Board actual = board.insert(index, coloredBall);
        assertThat(actual)
                .isEqualTo(expected);
    }

    private List<ColoredBall> toBalls(String board) {
        if (board == null) {
            return List.of();
        }
        List<ColoredBall> coloredBalls = new ArrayList<>();
        for (int i = 0; i < board.length(); i++) {
            char current = board.charAt(i);
            coloredBalls.add(new ColoredBall(current));
        }
        return coloredBalls;
    }
}