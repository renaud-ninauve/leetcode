package fr.ninauve.renaud.leetcode.wordladder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// https://leetcode.com/problems/word-ladder/
public class WordLadder {
    private static final char ANY_CHAR = '?';

    Neighbours neighbours;
    String end;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        this.neighbours = new Neighbours();
        for(String word: wordList) {
            neighbours.add(word);
        }
        this.end = endWord;
        return ladderLength(Set.of(beginWord), 1);
    }

    int ladderLength(Collection<String> current, int hop) {
        if (current.contains(end)) {
            return hop;
        }
        neighbours.removeAll(current);
        Collection<String> currentNeighbours = neighbours.findNeighbours(current);

        if (currentNeighbours.isEmpty()) {
            return 0;
        }
        return ladderLength(currentNeighbours, hop + 1);
    }

    static class Neighbours {
        private final Map<String, Set<String>> neighbours = new HashMap<>();

        void add(String word) {
            for(String key: keys(word)) {
                Set<String> currentNeighbours = neighbours.getOrDefault(key, new HashSet<>());
                currentNeighbours.add(word);
                neighbours.put(key, currentNeighbours);
            }
        }

        void removeAll(Collection<String> words) {
            for(Set<String> values: neighbours.values()) {
                values.removeAll(words);
            }
        }

        Collection<String> findNeighbours(Collection<String> words) {
            return words.stream()
                    .map(this::findNeighbours)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());
        }

        Collection<String> findNeighbours(String word) {
            List<String> keys = keys(word);
            return keys.stream()
                    .map(k -> neighbours.getOrDefault(k, Set.of()))
                    .flatMap(Set::stream)
                    .filter(w -> !w.equals(word))
                    .collect(Collectors.toSet());
        }

        private List<String> keys(String word) {
            return IntStream.range(0, word.length())
                    .mapToObj(i -> i == 0 ? ANY_CHAR + word.substring(1)
                            : word.substring(0, i) + ANY_CHAR + word.substring(i+1))
                    .toList();
        }
    }
}
