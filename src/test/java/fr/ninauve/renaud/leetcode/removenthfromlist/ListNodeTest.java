package fr.ninauve.renaud.leetcode.removenthfromlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ListNodeTest {

    static Stream<Arguments> listNode() {
        return Stream.of(
                Arguments.of(
                        List.of(),
                        null
                ),
                Arguments.of(
                        List.of(1),
                        new ListNode(1, null)
                ),
                Arguments.of(
                        List.of(1, 2, 3),
                        new ListNode(1, new ListNode(2, new ListNode(3)))
                ));
    }

    @ParameterizedTest
    @MethodSource
    void listNode(List<Integer> values, ListNode expected) {
        ListNode actual = ListNode.listNode(values);
        assertThat(actual).isEqualTo(expected);
    }
}