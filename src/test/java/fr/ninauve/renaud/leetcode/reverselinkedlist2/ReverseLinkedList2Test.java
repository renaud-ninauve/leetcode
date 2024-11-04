package fr.ninauve.renaud.leetcode.reverselinkedlist2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ReverseLinkedList2Test {

    static Stream<Arguments> reverseBetween() {
        return Stream.of(
                Arguments.of(null, 2, 4, null),
                Arguments.of(ListNode.listNode(List.of(1, 2, 3, 4, 5)), 2, 2, ListNode.listNode(List.of(1, 2, 3, 4, 5))),
                Arguments.of(ListNode.listNode(List.of(1, 2, 3, 4, 5)), 2, 4, ListNode.listNode(List.of(1, 4, 3, 2, 5))),
                Arguments.of(ListNode.listNode(List.of(1, 2, 3, 4, 5)), 1, 5, ListNode.listNode(List.of(5, 4, 3, 2, 1))),
                Arguments.of(ListNode.listNode(List.of(1, 2, 3, 4, 5)), 3, 4, ListNode.listNode(List.of(1, 2, 4, 3, 5)))
        );
    }

    @ParameterizedTest
    @MethodSource
    void reverseBetween(ListNode head, int left, int right, ListNode expected) {
        ListNode actual = new ReverseLinkedList2().reverseBetween(head, left, right);
        assertThat(actual).isEqualTo(expected);
    }
}