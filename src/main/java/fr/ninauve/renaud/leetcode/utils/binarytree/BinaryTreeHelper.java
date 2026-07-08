package fr.ninauve.renaud.leetcode.utils.binarytree;

class BinaryTreeHelper {

    /**
     * Parse to a binary tree.
     * <p>
     * The input string format should be of the form [0,1,2,3,4,5]
     * Each number of the array is the value of a node.
     * The array index is the pre-order position.
     * null stands for absence of node.
     * example:
     *               0
     *       1               2
     *   3       4       5       6
     *  7 8     9 10   11 12   13 14
     *
     * @param   preOrderBinaryTree  binary tree in pre-order
     * @return  binaryTree
     */
    static TreeNode parse(String preOrderBinaryTree) {
        return null;
    }

    static boolean nodesAreEquals(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.val == b.val
                && nodesAreEquals(a.left, b.left)
                && nodesAreEquals(a.right, b.right);
    }
}
