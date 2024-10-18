package fr.ninauve.renaud.leetcode.wordladder;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

// https://leetcode.com/problems/word-ladder/
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return 0;
    }

    Node toWordTree(String beginWord, List<String> wordList) {
        return new Node(beginWord, List.of());
    }

    boolean differsByOneLetter(String a, String b) {
        return IntStream.range(0, a.length())
                .filter(i -> a.charAt(i) != b.charAt(i))
                .limit(2)
                .count() == 1;
    }

    static class Node {
        final String value;
        final List<Node> children;

        Node(String value, List<Node> children) {
            this.value = value;
            this.children = children;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node node)) return false;
            return Objects.equals(value, node.value) && Objects.equals(children, node.children);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, children);
        }
    }
}
