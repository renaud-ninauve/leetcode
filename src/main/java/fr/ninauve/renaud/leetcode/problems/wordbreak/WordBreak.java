package fr.ninauve.renaud.leetcode.problems.wordbreak;

import java.util.*;
import java.util.stream.Stream;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String word : wordDict) {
            trie.add(word);
        }
        return trie.isComposedOfPrefixesOnly(s);
    }

    static class Trie {
        private final Node root = new Node(-1);

        void add(String word) {
            Node current = root;
            for (char c : word.toCharArray()) {
                current = current.addEdge(c);
            }
            current.setWordEnd();
        }

        boolean isComposedOfPrefixesOnly(String word) {
            List<Node> nodes = new ArrayList<>();
            nodes.add(root);
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                nodes = nodes.stream()
                        .map(node -> node.getEdge(c))
                        .filter(Objects::nonNull)
                        .flatMap(node -> {
                            if (!node.isWordEnd) {
                                return Stream.of(node);
                            }
                            return node.isEmpty()
                                    ? Stream.of(root)
                                    : Stream.of(node, root);
                        }).distinct()
                        .toList();
            }
            return nodes.stream()
                    .anyMatch(node -> node.index == -1);
        }
    }

    static class Node {
        private final int index;
        private final Map<Character, Node> edges = new HashMap<>();
        private boolean isWordEnd;

        Node(int index) {
            this.index = index;
        }

        Node addEdge(Character c) {
            return edges.computeIfAbsent(c, key -> new Node(index + 1));
        }

        Node getEdge(Character c) {
            return edges.get(c);
        }

        void setWordEnd() {
            this.isWordEnd = true;
        }

        boolean isEmpty() {
            return edges.isEmpty();
        }
    }
}
