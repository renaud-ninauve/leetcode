package fr.ninauve.renaud.leetcode.utils.binarytree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static fr.ninauve.renaud.leetcode.utils.binarytree.BinaryTreeHelper.nodesAreEquals;
import static org.assertj.core.api.Assertions.assertThat;

class ParseTest {

    @ParameterizedTest
    @CsvSource(delimiterString = " => ", value = {
            "1 => 0",
            "2 => 1",
            "3 => 1",
            "4 => 2",
            "5 => 2",
            "6 => 2",
            "7 => 2",
            "8 => 3",
            "15 => 3",
            "16 => 4"
    })
    void should_max_depth(int length, int expected) {
        int actual = BinaryTreeHelper.maxDepth(length);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_parse_root() {
        TreeNode actual = BinaryTreeHelper.parse("[42]");

        TreeNode expected = new TreeNode(42);
        assertThat(nodesAreEquals(actual, expected)).isTrue();
    }

    @Test
    void should_parse_depth3() {
        TreeNode actual = BinaryTreeHelper.parse("[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14]");

        List<TreeNode> expectedDepth3 = List.of(new TreeNode(7), new TreeNode(8), new TreeNode(9), new TreeNode(10), new TreeNode(11), new TreeNode(12), new TreeNode(13), new TreeNode(14));
        List<TreeNode> expectedDepth2 = List.of(new TreeNode(3, expectedDepth3.get(0), expectedDepth3.get(1)), new TreeNode(4, expectedDepth3.get(2), expectedDepth3.get(3)), new TreeNode(5, expectedDepth3.get(4), expectedDepth3.get(5)), new TreeNode(6, expectedDepth3.get(6), expectedDepth3.get(7)));
        List<TreeNode> expectedDepth1 = List.of(new TreeNode(1, expectedDepth2.get(0), expectedDepth2.get(1)), new TreeNode(2, expectedDepth2.get(2), expectedDepth2.get(3)));
        TreeNode expected = new TreeNode(0, expectedDepth1.get(0), expectedDepth1.get(1));
        assertThat(nodesAreEquals(actual, expected)).isTrue();
    }

    @Test
    void should_parse_depth3_nulls() {
        TreeNode actual = BinaryTreeHelper.parse("[0,1,2,3,4,null,null,7,8,9,10,null,null,null,null]");

        List<TreeNode> expectedDepth3 = List.of(new TreeNode(7), new TreeNode(8), new TreeNode(9), new TreeNode(10));
        List<TreeNode> expectedDepth2 = List.of(new TreeNode(3, expectedDepth3.get(0), expectedDepth3.get(1)), new TreeNode(4, expectedDepth3.get(2), expectedDepth3.get(3)));
        List<TreeNode> expectedDepth1 = List.of(new TreeNode(1, expectedDepth2.get(0), expectedDepth2.get(1)), new TreeNode(2, null, null));
        TreeNode expected = new TreeNode(0, expectedDepth1.get(0), expectedDepth1.get(1));
        assertThat(nodesAreEquals(actual, expected)).isTrue();
    }
}
