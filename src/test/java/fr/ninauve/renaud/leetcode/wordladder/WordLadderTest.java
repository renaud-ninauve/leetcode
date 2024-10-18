package fr.ninauve.renaud.leetcode.wordladder;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WordLadderTest {

    static Stream<Arguments> toWordTree() {
        return Stream.of(
                toWordTreeArguments()
                        .beginWord("a")
                        .wordList("a")
                        .expected(new WordLadder.Node("a", List.of())),

                toWordTreeArguments()
                        .beginWord("a")
                        .wordList("b")
                        .expected(new WordLadder.Node("a", List.of(new WordLadder.Node("b", List.of()))))
        );
    }

    @ParameterizedTest
    @MethodSource
    void toWordTree(String beginWord, List<String> wordList, WordLadder.Node expected) {
        WordLadder.Node actual = new WordLadder().toWordTree(beginWord, wordList);
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
        private String beginWord;
        private List<String> wordList;

        ToWordTreeArgumentsBuilder beginWord(String beginWord) {
            this.beginWord = beginWord;
            return this;
        }

        ToWordTreeArgumentsBuilder wordList(String... wordList) {
            this.wordList = Arrays.asList(wordList);
            return this;
        }

        Arguments expected(WordLadder.Node expected) {
            return Arguments.of(beginWord, wordList, expected);
        }
    }
}