package fr.ninauve.renaud.leetcode.removenthfromlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RemoveNthFromListTest {

    static Stream<Arguments> removeNthFromEnd() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5), 2, List.of(1, 2, 3, 5)),
                Arguments.of(List.of(1), 1, List.of()),
                Arguments.of(List.of(1, 2), 1, List.of(1)));
    }

    @ParameterizedTest
    @MethodSource
    void removeNthFromEnd(List<Integer> listNode, int nth, List<Integer> expected) {
        ListNode startList = ListNode.listNode(listNode);

        ListNode actual = new RemoveNthFromList().removeNthFromEnd(startList, nth);

        ListNode expectedList = ListNode.listNode(expected);
        assertThat(actual).isEqualTo(expectedList);
    }
}