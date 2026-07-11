package fr.ninauve.renaud.leetcode.problems.houserobber3;

import java.util.*;
import java.util.stream.Stream;

public class HouseRobber3 {
    TreeNode root;
    int maxDepth;
    Map<TreeNode, RobOrSkip> maxRobs = new LinkedHashMap<>();

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
            TreeNode left = root.left != null ? root.left : new TreeNode(0);
            TreeNode right = root.right != null ? root.right : new TreeNode(0);
            // right + total(left, 2, 3, 4)
            return max(
                    root.val + total(root, 3, 4, 5, 6),
                    left.val + right.val,
                    left.val + total(right, 1, 2),
                    right.val + total(left, 1, 2));
        }

        final List<TreeNode> nodes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            nodes.add(node);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        List<TreeNode> leafToRoot = nodes.reversed();

        for (TreeNode node : leafToRoot) {
            RobOrSkip maxLeft = max(node.left);
            RobOrSkip maxRight = max(node.right);
            RobOrSkip maxNode = new RobOrSkip(
                    node.val + maxLeft.skip() + maxRight.skip(),
                    maxLeft.max() + maxRight.max());
            maxRobs.put(node, maxNode);
        }

        RobOrSkip maxRoot = maxRobs.get(root);
        return maxRoot.max();
    }

    record RobOrSkip(int rob, int skip) {
        int max() {
            return Math.max(rob, skip);
        }
    }

    RobOrSkip max(TreeNode node) {
        if (node == null) {
            return new RobOrSkip(0, 0);
        }
        RobOrSkip result = maxRobs.get(node);
        if (result == null) {
            throw new IllegalArgumentException("should not be null");
        }
        return result;
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
                    continue;
                }
                TreeNode parent = parents.get(parentIndex);
                if (parent == null) {
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
