package fr.ninauve.renaud.leetcode.utils.binarytree;

import org.junit.jupiter.api.Test;

import static fr.ninauve.renaud.leetcode.utils.binarytree.BinaryTreeHelper.nodesAreEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class EqualsTest {

    @Test
    void null_null() {
        boolean actual = nodesAreEquals(null, null);
        assertThat(actual).isTrue();
    }

    @Test
    void null_notnull() {
        boolean actual = nodesAreEquals(null, new TreeNode(42));
        assertThat(actual).isFalse();
    }

    @Test
    void notnull_null() {
        boolean actual = nodesAreEquals(new TreeNode(42), null);
        assertThat(actual).isFalse();
    }

    @Test
    void same_val() {
        boolean actual = nodesAreEquals(new TreeNode(42), new TreeNode(42));
        assertThat(actual).isTrue();
    }

    @Test
    void different_val() {
        boolean actual = nodesAreEquals(new TreeNode(42), new TreeNode(666));
        assertThat(actual).isFalse();
    }

    @Test
    void same_children() {
        TreeNode a = new TreeNode(0, new TreeNode(1), new TreeNode(2));
        TreeNode b = new TreeNode(0, new TreeNode(1), new TreeNode(2));
        boolean actual = nodesAreEquals(a, b);
        assertThat(actual).isTrue();
    }

    @Test
    void different_children() {
        TreeNode a = new TreeNode(0, new TreeNode(1), new TreeNode(2));
        TreeNode b = new TreeNode(0, new TreeNode(666), new TreeNode(666));
        boolean actual = nodesAreEquals(a, b);
        assertThat(actual).isFalse();
    }

    @Test
    void same_tree() {
        TreeNode leftA = new TreeNode(1, new TreeNode(3), new TreeNode(4));
        TreeNode rightA = new TreeNode(2, new TreeNode(5), new TreeNode(6));
        TreeNode a = new TreeNode(0, leftA, rightA);

        TreeNode leftB = new TreeNode(1, new TreeNode(3), new TreeNode(4));
        TreeNode rightB = new TreeNode(2, new TreeNode(5), new TreeNode(6));
        TreeNode b = new TreeNode(0, leftB, rightB);
        boolean actual = nodesAreEquals(a, b);
        assertThat(actual).isTrue();
    }

    @Test
    void different_tree() {
        TreeNode leftA = new TreeNode(1, new TreeNode(3), new TreeNode(4));
        TreeNode rightA = new TreeNode(2, new TreeNode(5), new TreeNode(6));
        TreeNode a = new TreeNode(0, leftA, rightA);

        TreeNode leftB = new TreeNode(1, new TreeNode(3), new TreeNode(4));
        TreeNode rightB = new TreeNode(2, new TreeNode(5), new TreeNode(666));
        TreeNode b = new TreeNode(0, leftB, rightB);
        boolean actual = nodesAreEquals(a, b);
        assertThat(actual).isFalse();
    }

    @Test
    void different_tree_null() {
        TreeNode leftA = new TreeNode(1, new TreeNode(3), new TreeNode(4));
        TreeNode rightA = new TreeNode(2, new TreeNode(5), new TreeNode(6));
        TreeNode a = new TreeNode(0, leftA, rightA);

        TreeNode leftB = new TreeNode(1, new TreeNode(3), new TreeNode(4));
        TreeNode rightB = new TreeNode(2, new TreeNode(5), null);
        TreeNode b = new TreeNode(0, leftB, rightB);
        boolean actual = nodesAreEquals(a, b);
        assertThat(actual).isFalse();
    }
}
