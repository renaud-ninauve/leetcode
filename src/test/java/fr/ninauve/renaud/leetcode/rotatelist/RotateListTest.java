package fr.ninauve.renaud.leetcode.rotatelist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RotateListTest {

    static Stream<Arguments> rotateRight() {
        return Stream.of(
                Arguments.of(ListNode.listNode(List.of(1, 2, 3)), 0, ListNode.listNode(List.of(1, 2, 3))),
                Arguments.of(ListNode.listNode(List.of(1)), 3, ListNode.listNode(List.of(1))),
                Arguments.of(ListNode.listNode(List.of(1, 2, 3, 4, 5)), 1, ListNode.listNode(List.of(5, 1, 2, 3, 4))),
                Arguments.of(ListNode.listNode(List.of(1, 2, 3, 4, 5)), 2, ListNode.listNode(List.of(4, 5, 1, 2, 3))),
                Arguments.of(ListNode.listNode(List.of(1, 2, 3, 4, 5)), 3, ListNode.listNode(List.of(3, 4, 5, 1, 2))),
                Arguments.of(ListNode.listNode(List.of(1, 2, 3, 4, 5)), 4, ListNode.listNode(List.of(2, 3, 4, 5, 1))),
                Arguments.of(ListNode.listNode(List.of(1, 2, 3, 4, 5)), 5, ListNode.listNode(List.of(1, 2, 3, 4, 5))),
                Arguments.of(ListNode.listNode(List.of(1, 2, 3, 4, 5)), 6, ListNode.listNode(List.of(5, 1, 2, 3, 4))),
                Arguments.of(ListNode.listNode(List.of(1, 2, 3, 4, 5)), 15, ListNode.listNode(List.of(1, 2, 3, 4, 5))),
                Arguments.of(ListNode.listNode(List.of(1, 2, 3, 4, 5)), 16, ListNode.listNode(List.of(5, 1, 2, 3, 4)))
        );
    }

    @ParameterizedTest
    @MethodSource
    void rotateRight(ListNode head, int k, ListNode expected) {
        ListNode actual = new RotateList().rotateRight(head, k);
        assertThat(actual).isEqualTo(expected);
    }
}