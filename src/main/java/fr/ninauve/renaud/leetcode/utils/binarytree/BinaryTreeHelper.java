package fr.ninauve.renaud.leetcode.utils.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class BinaryTreeHelper {

    /**
     * Parse to a binary tree.
     * <p>
     * The input string format should be of the form [0,1,2,3,4,5]
     * Each number of the array is the value of a node.
     * The array index is the pre-order position.
     * null stands for absence of node.
     * example:
     * 0
     * 1               2
     * 3       4       5       6
     * 7 8     9 10   11 12   13 14
     *
     * @param preOrderBinaryTree binary tree in pre-order
     * @return binaryTree
     */
    static TreeNode parse(String preOrderBinaryTree) {
        String[] preOrderArray = preOrderBinaryTree.substring(1, preOrderBinaryTree.length() - 1).split(",");
        int length = preOrderArray.length;
        if (length == 0) {
            return null;
        }

        int maxDepth = maxDepth(length);
        TreeNode root = parseValue(preOrderArray[0]);
        List<TreeNode> parents = List.of(root);
        for (int depth = 1; depth <= maxDepth; depth++) {
            int countAtDepth = 1 << depth;
            for (int i = 0; i < countAtDepth; i++) {
                int index = countAtDepth - 1 + i;
                TreeNode node = index < preOrderArray.length ? parseValue(preOrderArray[index]) : null;
                TreeNode parent = parents.get(i / 2);
                if (parent == null && node != null) {
                    throw new IllegalArgumentException("");
                } else if (node == null) {
                    continue;
                }
                if (i % 2 == 0) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
            }
            parents = parents.stream()
                    .flatMap(parent -> parent != null ? Stream.of(parent.left, parent.right) : Stream.of(null, null))
                    .toList();
        }
        return root;
    }

    static List<TreeNode> preOrder(TreeNode start, int maxDepth) {
        if (maxDepth == 0) {
            return List.of(start);
        }
        List<TreeNode> result = new ArrayList<>();
        result.add(start);
        List<TreeNode> parents = List.of(start);
        for (int depth = 1; depth <= maxDepth; depth++) {
            int nbNodes = 1 << depth;
            int firstChildIndexInResult = result.size();
            for (int i = 0; i < nbNodes; i++) {
                int parentIndex = i / 2;
                if (parentIndex >= parents.size()) {
                    result.add(null);
                    continue;
                }
                TreeNode parent = parents.get(parentIndex);
                if (parent == null) {
                    result.add(null);
                    continue;
                }
                if (i % 2 == 0) {
                    result.add(parent.left);
                } else {
                    result.add(parent.right);
                }
            }
            parents = new ArrayList<>(result.subList(firstChildIndexInResult, result.size()));
        }
        return result;
    }

    static int maxDepth(int length) {
        int pow2 = 0;
        int shiftLeft = length;
        while ((shiftLeft = shiftLeft >> 1) > 0) {
            pow2++;
        }
        return pow2;
    }

    static TreeNode parseValue(String value) {
        return "null".equals(value)
                ? null
                : new TreeNode(Integer.parseInt(value));
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
