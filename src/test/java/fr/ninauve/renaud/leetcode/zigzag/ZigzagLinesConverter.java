package fr.ninauve.renaud.leetcode.zigzag;

import java.util.stream.Collectors;

public class ZigzagLinesConverter {
    public static String toOneLineString(String zigzagLines) {
        return zigzagLines.lines()
                .map(line -> line.replaceAll("\\s", ""))
                .collect(Collectors.joining());
    }
}
