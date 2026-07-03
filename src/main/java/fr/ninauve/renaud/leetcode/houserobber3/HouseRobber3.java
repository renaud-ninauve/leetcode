package fr.ninauve.renaud.leetcode.houserobber3;

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
            int total1 = children(root).mapToInt(n -> n.val).sum();
            int total2 = children(root)
                    .flatMap(this::children)
                    .mapToInt(n -> n.val).sum();
            return Math.max(total0 + total2, total1);
        }
        for (int depth = maxDepth - 2; depth >= 0; depth--) {
            List<TreeNode> nodes = nodesAt(depth);
            for (TreeNode node : nodes) {
                if (depth == maxDepth - 2) {
                    node.val = node.val + total2(node);
                } else {
                    List<TreeNode> children2 = children(node).flatMap(this::children).toList();
                    // 4
                    int total2 = children2.stream().mapToInt(n -> n.val).sum();
                    List<TreeNode> children3 = children2.stream().flatMap(this::children).toList();
                    int total3 = children3.stream().mapToInt(n -> n.val).sum();

                    int maxLeft = max23(children2, true);
                    int maxRight = max23(children2, false);

                    node.val = node.val + maxLeft + maxRight;
                }
            }
            System.out.println(depth + " => [" + nodes.stream().map(n -> n.val + "").collect(Collectors.joining(",")) + "]");
        }
        return Math.max(root.val, children(root).mapToInt(n -> n.val).sum());
    }

    private int max23(List<TreeNode> children2, boolean left) {
        int delta = left ? 0 : 2;
        return max(
                children2.get(delta).val + children2.get(1 + delta).val,
                total1(children2.get(delta)) + total1(children2.get(1 + delta)),
                children2.get(delta).val + total1(children2.get(1 + delta)),
                children2.get(1 + delta).val + total1(children2.get(delta)));
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

    int total1(TreeNode parent) {
        return Stream.of(parent.left, parent.right)
                .filter(Objects::nonNull)
                .mapToInt(n -> n.val)
                .sum();
    }

    int total2(TreeNode parent) {
        return Stream.of(parent.left, parent.right)
                .filter(Objects::nonNull)
                .flatMap(n -> Stream.of(n.left, n.right))
                .filter(Objects::nonNull)
                .mapToInt(n -> n.val)
                .sum();
    }

    List<TreeNode> nodesAt(int depth) {
        if (depth == 0) {
            return List.of(root);
        }
        int currentDepth = 0;
        List<TreeNode> nodes = List.of(root);
        while (!nodes.isEmpty() && currentDepth < depth) {
            currentDepth++;
            nodes = nodes.stream()
                    .flatMap(n -> Stream.of(n.left, n.right))
                    .map(n -> n != null ? n : new TreeNode(0))
                    .toList();
        }
        return nodes;
    }
}
