package fr.ninauve.renaud.leetcode.wordladder;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WordLadderTest {

    static Stream<Arguments> toWordTree() {
        return Stream.of(
                toWordTreeArguments()
                        .wordList("a")
                        .expectNode("a", List.of())
                        .build(),

                toWordTreeArguments()
                        .wordList("a", "b")
                        .expectNode("a", List.of("b"))
                        .expectNode("b", List.of("a"))
                        .build(),

                toWordTreeArguments()
                        .wordList("aaa", "abc",  "aba", "xxx", "abb")
                        .expectNode("aaa", List.of("aba"))
                        .expectNode("aba", List.of("aaa", "abc", "abb"))
                        .expectNode("abb", List.of("aba", "abc"))
                        .expectNode("abc", List.of("aba", "abb"))
                        .expectNode("xxx", List.of())
                        .build()
        );
    }

    static Stream<Arguments> ladderLength() {
        return Stream.of(
                Arguments.of("a", "a", List.of("a"), 1),
                Arguments.of("a", "b", List.of("c"), 0),
                Arguments.of("a", "b", List.of("a", "b"), 2),
                Arguments.of("aaa", "abc", List.of("aaa", "abc",  "aba", "xxx", "abb"), 3),
                Arguments.of("hit", "cog", List.of("hot","dot","dog","lot","log","cog"), 5)
        );
    }

    @ParameterizedTest
    @MethodSource
    void toWordTree(List<String> wordList, Map<String, WordLadder.Node> expected) {
        Map<String, WordLadder.Node> actual = new WordLadder().toWordTree(wordList);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource
    void ladderLength(String beginWord, String endWord, List<String> wordList, int expected) {
        int actual = new WordLadder().ladderLength(beginWord, endWord, wordList);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "aaaa;aaaa;false",
            "aaaa;aaab;true",
            "aaaa;aabb;false"
    })
    void differsByOneLetter(String a, String b, boolean expected) {
        boolean actual = new WordLadder().differsByOneLetter(a, b);
        assertThat(actual).isEqualTo(expected);
    }

    static ToWordTreeArgumentsBuilder toWordTreeArguments() {
        return new ToWordTreeArgumentsBuilder();
    }

    static class ToWordTreeArgumentsBuilder {
        private List<String> wordList;
        private Map<String, WordLadder.Node> expected = new HashMap<>();

        ToWordTreeArgumentsBuilder wordList(String... wordList) {
            this.wordList = Arrays.asList(wordList);
            return this;
        }

        ToWordTreeArgumentsBuilder expectNode(String word, List<String> words) {
            expected.put(word, new WordLadder.Node(word, new HashSet<>(words)));
            return this;
        }

        Arguments build() {
            return Arguments.of(wordList, expected);
        }
    }
}