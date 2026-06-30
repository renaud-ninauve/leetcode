package fr.ninauve.renaud.leetcode.zuma;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Zuma {
    private static final Pattern TRI_PATTERN = Pattern.compile("RRR|YYY|BBB|GGG|WWW");

    public int findMinStep(String board, String hand) {
        NavigableSet<Group> groups = toGroups(board);
        Map<Character, Integer> maxCountByColor = maxCountByColor(groups);
        Map<Character, Integer> handColors = sumByColor(hand);
        Map<Character, Integer> filteredHandColors = handColors.entrySet().stream()
                .filter(hc -> {
                    Character color = hc.getKey();
                    Integer handCount = hc.getValue();
                    Integer maxBoardCount = maxCountByColor.getOrDefault(color, 0);
                    return maxBoardCount + handCount >= 3;
                }).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        if (filteredHandColors.isEmpty()) {
            return -1;
        }
        return 0;
    }

    private Map<Character, Integer> maxCountByColor(NavigableSet<Group> groups) {
        record ColorCount(char color, int count) {
        }
        return groups.stream()
                .map(g -> new ColorCount(g.color(), g.length()))
                .collect(Collectors.groupingBy(ColorCount::color,
                        Collectors.mapping(
                                ColorCount::count,
                                Collectors.reducing(0, Math::max)
                        )));
    }

    private Map<Character, Integer> sumByColor(String hand) {
        Map<Character, Integer> sumByColor = new HashMap<>();
        for (char c : hand.toCharArray()) {
            sumByColor.compute(c, (color, previousSum) -> previousSum == null ? 1 : previousSum + 1);
        }
        return sumByColor;
    }

    NavigableSet<Group> toGroups(String board) {
        NavigableSet<Group> groups = new TreeSet<>(Comparator.comparing(Group::start));
        int sameCount = 1;
        char previous = 'X';
        for (int i = 0; i < board.length(); i++) {
            char current = board.charAt(i);
            if (i == 0) {
                previous = current;
                continue;
            }
            if (current == previous) {
                sameCount++;
            } else {
                groups.add(new Group(previous, i - sameCount, sameCount));
                sameCount = 1;
            }
            if (i == board.length() - 1) {
                groups.add(new Group(current, i - sameCount + 1, sameCount));
            }
            previous = current;
        }
        return groups;
    }

    public String deleteTris(String board) {
        String result = board;
        Matcher matcher;
        while ((matcher = TRI_PATTERN.matcher(result)).find()) {
            int start = matcher.start();
            int end = matcher.end();
            final String beforeMatch = start > 0 ? result.substring(0, start) : "";
            final String afterMatch = end < result.length() ? result.substring(end) : "";
            result = beforeMatch + afterMatch;
        }
        return result;
    }

    record Group(char color, int start, int length) {
    }
}
