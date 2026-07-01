package fr.ninauve.renaud.leetcode.wordbreak;

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
            List<Completed> completed = new ArrayList<>();
            List<NodeAndCompletedWords> nodes = new ArrayList<>();
            nodes.add(new NodeAndCompletedWords(0, root, 0));
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                nodes = nodes.stream()
                        .map(node -> node.getEdge(c))
                        .filter(Objects::nonNull)
                        .flatMap(node -> {
                            if (!node.isWordEnd()) {
                                return Stream.of(node);
                            }
                            int completedLength = node.getIndex() + 1;
                            completed.add(new Completed(node.start, completedLength));
                            NodeAndCompletedWords restart = new NodeAndCompletedWords(node.start + completedLength, root, completedLength);
                            return node.isEmpty()
                                    ? Stream.of(restart)
                                    : Stream.of(node, restart);
                        }).toList();
            }
            return nodes.stream()
                    .anyMatch(node -> node.getIndex() == -1);
        }
    }

    record Completed(int start, int length) {
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

        void setWordEnd() {
            this.isWordEnd = true;
        }

        boolean isEmpty() {
            return edges.isEmpty();
        }
    }

    static class NodeAndCompletedWords {
        final int start;
        final Node node;
        final int completedLength;

        public NodeAndCompletedWords(int start, Node node, int completedLength) {
            this.start = start;
            this.node = node;
            this.completedLength = completedLength;
        }

        int getCompletedLength() {
            return completedLength;
        }

        NodeAndCompletedWords getEdge(Character c) {
            Node edge = node.edges.get(c);
            return edge == null ? null : new NodeAndCompletedWords(start, edge, completedLength);
        }

        int getIndex() {
            return node.index;
        }

        boolean isWordEnd() {
            return node.isWordEnd;
        }

        boolean isEmpty() {
            return node.isEmpty();
        }
    }
}
