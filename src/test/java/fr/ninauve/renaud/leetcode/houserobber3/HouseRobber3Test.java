package fr.ninauve.renaud.leetcode.houserobber3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HouseRobber3Test {

    //      0
//    0             0
//   0   100       0    0
// 0  100 0  0   0 0   0    0

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
            "[2,1,3,null,4] => 7"
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
        List<TreeNode> parents = new ArrayList<>();
        parents.add(root);
        int parentIndex = 0;
        for (int i = 1; i < array.length; i++) {
            String valueStr = array[i];
            if (!Objects.equals(valueStr, "null")) {
                int value = Integer.parseInt(valueStr);
                if (parentIndex % 2 == 0) {
                    parents.get(parentIndex / 2).left = new TreeNode(value);
                } else {
                    parents.get(parentIndex / 2).right = new TreeNode(value);
                }
            }
            parentIndex++;
            if (parentIndex >= parents.size() * 2) {
                parentIndex = 0;
                parents = parents.stream()
                        .flatMap(p -> p == null ? Stream.of(null, null) : Stream.of(p.left, p.right))
                        .toList();
            }
        }
        return root;
    }
}