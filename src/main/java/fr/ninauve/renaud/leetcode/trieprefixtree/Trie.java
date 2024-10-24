package fr.ninauve.renaud.leetcode.trieprefixtree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// https://leetcode.com/problems/implement-trie-prefix-tree/
class Trie {
    Node root = new Node(null, true);

    public Trie() {

    }

    public void insert(String word) {
        Node currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            Optional<Node> matchingNode = currentNode.children.stream()
                    .filter(n -> Objects.equals(n.value, currentChar))
                    .findFirst();
            if (matchingNode.isEmpty()) {
                Node newNode = new Node(currentChar);
                currentNode.children.add(newNode);
                currentNode = newNode;
            } else {
                currentNode = matchingNode.get();
            }
        }
        currentNode.endWord = true;
    }

    public boolean search(String word) {
        return findNodeStartingWith(word).stream().anyMatch(Node::isEndWord);
    }

    public boolean startsWith(String prefix) {
        return findNodeStartingWith(prefix).isPresent();
    }

    private Optional<Node> findNodeStartingWith(String prefix) {
        Node currentNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currentChar = prefix.charAt(i);
            Optional<Node> matchingNode = currentNode.children.stream()
                    .filter(n -> Objects.equals(n.value, currentChar))
                    .findFirst();
            if (matchingNode.isEmpty()) {
                return Optional.empty();
            }
            currentNode = matchingNode.get();
        }
        return Optional.of(currentNode);
    }

    static class Node {
        Character value;
        List<Node> children = new ArrayList<>();
        boolean endWord = false;

        public Node(Character value) {
            this.value = value;
        }

        public Node(Character value, boolean endWord) {
            this.value = value;
            this.endWord = endWord;
        }

        boolean isEndWord() {
            return endWord;
        }
    }
}