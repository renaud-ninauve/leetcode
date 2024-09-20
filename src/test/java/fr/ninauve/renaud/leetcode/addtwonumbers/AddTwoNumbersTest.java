package fr.ninauve.renaud.leetcode.addtwonumbers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AddTwoNumbersTest {

    static Stream<Arguments> add_two_numbers() {
        return Stream.of(
                Arguments.of(
                        listNode(2),
                        listNode(3),
                        listNode(5)
                ),
                Arguments.of(
                        listNode(1, 1, 1),
                        listNode(2, 3, 4),
                        listNode(3, 4, 5)
                ));
    }

    @ParameterizedTest
    @MethodSource
    void add_two_numbers(ListNode a, ListNode b, ListNode expected) {
        ListNode actual = new AddTwoNumbers().addTwoNumbers(a, b);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_create_listnode() {
        ListNode actual = listNode(1, 2, 3);

        assertThat(actual).isEqualTo(new ListNode(1, new ListNode(2, new ListNode(3))));
    }

    static ListNode listNode(int... values) {
        ListNode numbers = null;
        for (int i = values.length - 1; i >= 0; i--) {
            int value = values[i];
            if (numbers == null) {
                numbers = new ListNode(value);
                continue;
            }
            numbers = new ListNode(value, numbers);
        }
        return numbers;
    }
}