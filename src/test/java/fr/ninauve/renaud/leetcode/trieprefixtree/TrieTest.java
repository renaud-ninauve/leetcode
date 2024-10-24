package fr.ninauve.renaud.leetcode.trieprefixtree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TrieTest {

    static Stream<Arguments> search() {
        return Stream.of(
                Arguments.of(List.of(), "toto", false),
                Arguments.of(List.of(), "", true)
        );
    }

    static Stream<Arguments> startsWith() {
        return Stream.of(
                Arguments.of(List.of(), "toto", false),
                Arguments.of(List.of(), "", true)
        );
    }

    @ParameterizedTest
    @MethodSource
    void search(List<String> inserts, String search, boolean expected) {
        Trie trie = new Trie();
        for (String insert : inserts) {
            trie.insert(insert);
        }

        boolean actual = trie.search(search);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void startsWith(List<String> inserts, String prefix, boolean expected) {
        Trie trie = new Trie();
        for (String insert : inserts) {
            trie.insert(insert);
        }

        boolean actual = trie.startsWith(prefix);

        assertThat(actual).isEqualTo(expected);
    }
}