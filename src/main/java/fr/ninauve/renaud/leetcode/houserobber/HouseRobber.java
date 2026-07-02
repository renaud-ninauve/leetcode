package fr.ninauve.renaud.leetcode.houserobber;

public class HouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] max = new int[nums.length];
        max[0] = nums[0];
        max[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            int num = nums[i];
            if (i == 2) {
                max[i] = num + max[i - 2];
            } else {
                max[i] = num + Math.max(max[i - 3], max[i - 2]);
            }
        }

        return Math.max(max[nums.length - 2], max[nums.length - 1]);
    }
}
