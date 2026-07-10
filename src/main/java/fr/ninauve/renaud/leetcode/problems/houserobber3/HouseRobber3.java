package fr.ninauve.renaud.leetcode.problems.houserobber3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HouseRobber3 {
    TreeNode root;
    int maxDepth;

    int rob(TreeNode root) {
        this.root = root;
        this.maxDepth = maxDepth(root);
        return rob();
    }

    int rob() {
        int total0 = root.val;
        if (maxDepth == 0) {
            return total0;
        }
        if (maxDepth == 1) {
            int total1 = children(root).mapToInt(n -> n.val).sum();
            return Math.max(total0, total1);
        }
        if (maxDepth == 2) {
            //      0
            //   1    2
            //  3  4 5 6
            TreeNode left = root.left != null ? root.left : new TreeNode(0);
            TreeNode right = root.right != null ? root.right : new TreeNode(0);
            // right + total(left, 2, 3, 4)
            return max(
                    root.val + total(root, 3, 4, 5, 6),
                    left.val + right.val,
                    left.val + total(right, 1, 2),
                    right.val + total(left, 1, 2));
        }
        //            0
        //      1             2
        //   3    4       5      6
        //  7  8 9 10   11 12   13 14


//      0
//   1    2
//  3  4 5 6

        // 0 3 4
        // 0 7 8 9 10
        // 0 3 9 10
        // 0 7 8 4

        for (int depth = maxDepth - 2; depth >= 0; depth--) {
            List<TreeNode> nodes = nodesAt(depth);
            for (TreeNode node : nodes) {
                if (node == null) {
                    continue;
                }
                if (depth == maxDepth - 2) {
                    node.val = node.val + total2(node);
                } else {
                    TreeNode left = node.left != null ? node.left : new TreeNode(0);
                    TreeNode right = node.right != null ? node.right : new TreeNode(0);
                    int maxLeft = max(
                            total(left, 3, 4, 5, 6),
                            total(left, 1, 2),
                            total(left, 1, 5, 6),
                            total(left, 2, 3, 4));
                    int maxRight = max(
                            total(right, 3, 4, 5, 6),
                            total(right, 1, 2),
                            total(right, 1, 5, 6),
                            total(right, 2, 3, 4));
                    node.val = node.val + maxLeft + maxRight;
                }
            }
        }
        //            0
        //      1             2
        //   3    4       5      6
        //  7  8 9 10   11 12   13 14

        //      0
        //   1    2
        //  3  4 5 6
        TreeNode left = root.left != null ? root.left : new TreeNode(0);
        TreeNode right = root.right != null ? root.right : new TreeNode(0);
        // right + total(left, 2, 3, 4)
        return max(
                root.val,
                left.val + right.val,
                left.val + total(right, 1, 2),
                left.val + total(right, 3, 4, 5, 6),
                left.val + total(right, 1, 5, 6),
                left.val + total(right, 2, 3, 4),
                right.val + total(left, 1, 2),
                right.val + total(left, 3, 4, 5, 6),
                right.val + total(left, 1, 5, 6),
                right.val + total(left, 2, 3, 4));
    }

    static int max(int... numbers) {
        return Arrays.stream(numbers).max().orElse(0);
    }

    int maxDepth(TreeNode root) {
        int depth = -1;
        List<TreeNode> nodes = List.of(root);
        while (!nodes.isEmpty()) {
            depth++;
            nodes = nodes.stream()
                    .flatMap(n -> Stream.of(n.left, n.right))
                    .filter(Objects::nonNull)
                    .toList();
        }
        return depth;
    }

    Stream<TreeNode> children(TreeNode parent) {
        return Stream.of(parent.left, parent.right)
                .map(n -> n != null ? n : new TreeNode(0));
    }

    // 2**4 - 2**3 = length
    // 2**depth - 1 = start
    //            0
//      1             2
//   3    4       5      6
//  7  8 9 10   11 12   13 14
    int total(TreeNode parent, int... indexes) {
        return Arrays.stream(indexes)
                .map(i -> {
                    final int depth;
                    if (i >= 7) {
                        depth = 3;
                    } else if (i >= 3) {
                        depth = 2;
                    } else if (i >= 1) {
                        depth = 1;
                    } else {
                        depth = 0;
                    }
                    int start = (1 << depth) - 1;
                    int childIndex = i - start;
                    final List<TreeNode> childrenAtDepth = nodesAt(parent, depth);
                    if (childIndex >= childrenAtDepth.size()) {
                        return 0;
                    }
                    TreeNode child = childrenAtDepth.get(childIndex);
                    return child != null ? child.val : 0;
                }).sum();
    }

    int total1(TreeNode parent) {
        return Stream.of(parent.left, parent.right)
                .filter(Objects::nonNull)
                .mapToInt(n -> n.val)
                .sum();
    }

    int total2(TreeNode parent) {
        if (parent == null) {
            return 0;
        }
        return Stream.of(parent.left, parent.right)
                .filter(Objects::nonNull)
                .flatMap(n -> Stream.of(n.left, n.right))
                .filter(Objects::nonNull)
                .mapToInt(n -> n.val)
                .sum();
    }

    List<TreeNode> nodesAt(TreeNode start, int depth) {
        if (depth == 0) {
            return List.of(start);
        }
        List<TreeNode> parents = List.of(start);
        for (int currentDepth = 1; currentDepth <= depth; currentDepth++) {
            int nodesCount = 1 << currentDepth;
            List<TreeNode> nodes = new ArrayList<>();
            for (int i = 0; i < nodesCount; i++) {
                int parentIndex = i / 2;
                if (parentIndex >= parents.size()) {
                    nodes.add(null);
                    nodes.add(null);
                    continue;
                }
                TreeNode parent = parents.get(parentIndex);
                if (parent == null) {
                    nodes.add(null);
                    nodes.add(null);
                    continue;
                }
                if (i % 2 == 0) {
                    nodes.add(parent.left);
                } else {
                    nodes.add(parent.right);
                }
            }
            parents = nodes;
        }
        return parents;
    }

    List<TreeNode> nodesAt(int depth) {
        return nodesAt(root, depth);
    }
}
