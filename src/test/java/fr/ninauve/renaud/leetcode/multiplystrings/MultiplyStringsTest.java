package fr.ninauve.renaud.leetcode.multiplystrings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MultiplyStringsTest {

    @ParameterizedTest
    @CsvSource(delimiterString = ", ", value = {
            "3, 4, 12",
            "2, 12, 24",
            "5, 53, 265",
            "12, 23, 276",
            "67, 89, 5963",
            "0, 9133, 0",

            // examples from leetcode
            "123, 456, 56088",
            "9133, 0, 0"
    })
    void multiply(String a, String b, String expected) {
        String actual = new MultiplyStrings().multiply(a, b);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> toListNode() {
        return Stream.of(
                Arguments.of("3", listNode(3)),
                Arguments.of("12345", listNode(5, 4, 3, 2, 1)))
                ;
    }

    @ParameterizedTest
    @MethodSource
    void toListNode(String number, ListNode expected) {
        ListNode actual = new MultiplyStrings().toListNode(number);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> listNodeToString() {
        return Stream.of(
                Arguments.of(listNode(3), "3"),
                Arguments.of(listNode(5, 4, 3, 2, 1), "12345"))
                ;
    }

    @ParameterizedTest
    @MethodSource
    void listNodeToString(ListNode listNode, String expected) {
        String actual = new MultiplyStrings().listNodeToString(listNode);
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