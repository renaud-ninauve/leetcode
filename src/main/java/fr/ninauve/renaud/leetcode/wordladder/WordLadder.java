package fr.ninauve.renaud.leetcode.wordladder;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// https://leetcode.com/problems/word-ladder/
public class WordLadder {
    Map<String, Node> graph;
    int minHop;
    String end;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        final List<String> allWords = new ArrayList<>(wordList);
        allWords.add(beginWord);

        this.graph = toWordTree(allWords);
        this.minHop = 0;
        this.end = endWord;

        ladderLength(graph.get(beginWord), 1, new HashSet<>());
        return minHop;
    }

    void ladderLength(Node current, int hop, Set<String> visited) {
        if (current.value.equals(end)) {
            this.minHop = minHop == 0 ? hop : Math.min(minHop, hop);
            return;
        }
        for (String child : current.children) {
            if (visited.contains(child)) {
                continue;
            }
            Set<String> newVisited = new HashSet<>(visited);
            newVisited.add(current.value);
            ladderLength(graph.get(child), hop + 1, newVisited);
        }
    }

    Map<String, Node> toWordTree(List<String> wordList) {
        final Map<String, Node> nodes = wordList.stream()
                .map(Node::new)
                .collect(Collectors.toMap(node -> node.value, Function.identity(), (a, b) -> a));

        final List<String> todo = new ArrayList<>(wordList);
        for (String word : wordList) {
            todo.remove(word);
            Node node = nodes.get(word);
            for (String otherWord : todo) {
                if (differsByOneLetter(word, otherWord)) {
                    Node otherNode = nodes.get(otherWord);
                    node.children.add(otherWord);
                    otherNode.children.add(word);
                }
            }
        }
        return nodes;
    }

    boolean differsByOneLetter(String a, String b) {
        return IntStream.range(0, a.length())
                .filter(i -> a.charAt(i) != b.charAt(i))
                .limit(2)
                .count() == 1;
    }

    static class Node {
        final String value;
        final Set<String> children;

        Node(String value) {
            this.value = value;
            this.children = new HashSet<>();
        }

        Node(String value, Set<String> children) {
            this.value = value;
            this.children = children;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node node)) return false;
            return Objects.equals(value, node.value)
                    && Objects.equals(children, node.children);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, children);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value='" + value + '\'' +
                    ", children=" + children +
                    '}';
        }
    }
}
