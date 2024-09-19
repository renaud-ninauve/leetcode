package fr.ninauve.renaud.leetcode.firstmissingpositive;

import java.util.Arrays;

// https://leetcode.com/problems/first-missing-positive/
public class Solution {

    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int previous = -1;
        boolean firstPositive = true;
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            if (current <= 0) {
                continue;
            }
            if (firstPositive) {
                firstPositive = false;
                if (current > 1) {
                    return 1;
                }
            } else if (current > previous + 1) {
                return previous + 1;
            }
            previous = current;
        }

        return firstPositive ? 1 : nums[nums.length - 1] + 1;
    }
}
