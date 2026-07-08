package fr.ninauve.renaud.leetcode.problems.houserobber3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HouseRobber3Test {

    //      0
//    0             0
//   0   100       0    0
// 0  100 0  0   0 0   0    0

    //            5
//      3             6
//   1    4       0      0
//  0 2
    // right + total(left, 2, 3, 4)
    @ParameterizedTest
    @CsvSource(delimiterString = " => ", value = {
            "[42] => 42",
            "[1,2,2] => 4",
            "[42,1,1] => 42",
            "[1,null,42] => 42",
            "[3,2,3,null,3,null,1] => 7",
            "[3,4,5,1,3,null,1] => 9",
            "[100,0,0,0,0,0,0,0,0,0,0,0,0,0,0] => 100",
            "[1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0] => 4",
            "[2,1,3,null,4] => 7",
            "[5,3,6,1,4,null,null,null,2] => 12",
            "[28,12,45,4,24,35,47,2,9,14,25,31,42,46,48,0,3,8,11,13,20,null,26,30,33,41,43,null,null,null,49,null,1,null,null,7,null,10,null,null,null,17,22,null,27,29,null,32,34,36,null,null,44,null,null,null,null,6,null,null,null,16,18,21,23,null,null,null,null,null,null,null,null,null,37,null,null,5,null,15,null,null,19,null,null,null,null,null,40,null,null,null,null,null,null,39,null,38] => 677"
    })
    void should_rob(String tree, int expected) {
        TreeNode root = parse(tree);
        int actual = new HouseRobber3().rob(root);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_maxDepth0() {
        TreeNode root = new TreeNode(0);
        HouseRobber3 houseRobber3 = new HouseRobber3();
        houseRobber3.root = root;
        int actual = houseRobber3.maxDepth(root);
        assertThat(actual).isEqualTo(0);
    }

    @Test
    void should_maxDepth1() {
        TreeNode root = new TreeNode(0, null, new TreeNode(1));
        HouseRobber3 houseRobber3 = new HouseRobber3();
        houseRobber3.root = root;
        int actual = houseRobber3.maxDepth(root);
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void should_maxDepth2() {
        TreeNode root = new TreeNode(0, null, new TreeNode(1, null, new TreeNode(2)));
        HouseRobber3 houseRobber3 = new HouseRobber3();
        houseRobber3.root = root;
        int actual = houseRobber3.maxDepth(root);
        assertThat(actual).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource(delimiterString = " => ", value = {
            "0 => [0]",
            "1 => [1,2]",
            "2 => [3,4,5,6]"
    })
    void should_depthValues(int depth, String expected) {
        TreeNode root = parse("[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,14]");
        HouseRobber3 houseRobber3 = new HouseRobber3();
        houseRobber3.root = root;
        List<TreeNode> actual = houseRobber3.nodesAt(depth);
        List<Integer> actualValues = actual.stream().map(n -> n.val).toList();
        List<Integer> exptectedValues = Arrays.stream(expected.substring(1, expected.length() - 1).split(","))
                .map(Integer::parseInt)
                .toList();
        assertThat(actualValues).containsExactlyElementsOf(exptectedValues);
    }

//            0
//    1             2
//   3   4       5    6
// 7  8 9  10   11 12 13 14

    static TreeNode parse(String tree) {
        String[] array = tree.substring(1, tree.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(array[0]));
        int length = array.length;
        int maxDepth = 0;
        int pow2 = 2;
        while (pow2 - 1 < length) {
            maxDepth++;
            pow2 = pow2 << 1;
        }

        List<TreeNode> parents = new ArrayList<>();
        parents.add(root);
        for (int depth = 1; depth <= maxDepth; depth++) {
            for (int i = 0; i < 1 << depth; i++) {
                int arrayIndex = (1 << depth) - 1 + i;
                String valueStr = arrayIndex < array.length ? array[arrayIndex] : "null";
                int parentIndex = i / 2;
                TreeNode parent = parents.get(parentIndex);
                if (parent == null) {
                    continue;
                }
                if (i % 2 == 0) {
                    parent.left = parseValue(valueStr);
                } else {
                    parent.right = parseValue(valueStr);
                }
            }
            parents = parents.stream()
                    .flatMap(n -> n != null ? Stream.of(n.left, n.right) : Stream.of(null, null))
                    .toList();
        }
        return root;
    }

    static TreeNode parseValue(String str) {
        if ("null".equals(str)) {
            return null;
        }
        return new TreeNode(Integer.parseInt(str));
    }
}