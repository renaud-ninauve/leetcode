package fr.ninauve.renaud.leetcode.utils.binarytree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PreOrderTest {

    @Test
    void root_to_maxDepth() {
        TreeNode tree = BinaryTreeHelper.parse("[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14]");

        assertThat(BinaryTreeHelper.preOrder(tree, 3))
                .extracting(n -> n.val)
                .containsExactly(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14);
    }

    @Test
    void root_to_depth2() {
        TreeNode tree = BinaryTreeHelper.parse("[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14]");

        assertThat(BinaryTreeHelper.preOrder(tree, 2))
                .extracting(n -> n.val)
                .containsExactly(0,1,2,3,4,5,6);
    }

    @Test
    void two_to_depth2() {
        TreeNode tree = BinaryTreeHelper.parse("[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14]");

        assertThat(BinaryTreeHelper.preOrder(tree.right, 2))
                .extracting(n -> n.val)
                .containsExactly(2,5,6,11,12,13,14);
    }

    @Test
    void root_to_maxDepth_nulls() {
        TreeNode tree = BinaryTreeHelper.parse("[0,1,2,null,4,5,6,null,null,9,10,11,12,13,14]");

        assertThat(BinaryTreeHelper.preOrder(tree, 3))
                .extracting(n -> n != null ? "" + n.val : "null")
                .containsExactly("0","1","2","null","4","5","6","null","null","9","10","11","12","13","14");
    }

    @Test
    void root_to_maxDepth_nulls2() {
        TreeNode tree = BinaryTreeHelper.parse("[0,1,2,3,4,5,6,7,8,9,10]");

        assertThat(BinaryTreeHelper.preOrder(tree, 3))
                .extracting(n -> n != null ? "" + n.val : "null")
                .containsExactly("0","1","2","3","4","5","6","7","8","9","10","null","null","null","null");
    }
}
