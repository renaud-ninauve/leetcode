package fr.ninauve.renaud.leetcode.houserobber2;

public class HouseRobber2 {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        if (length == 3) {
            return Math.max(Math.max(nums[0], nums[1]), nums[2]);
        }
        if (length == 4) {
            return Math.max(nums[0] + nums[2], nums[1] + nums[3]);
        }
        return Math.max(robFirstHouse(nums), dontRobFirstHouse(nums));
    }

    int robFirstHouse(int[] nums) {
        int length = nums.length;
        int end = length - 1;
        int[] max = new int[length];
        max[0] = 0;
        max[1] = 0;
        for (int i = 2; i < length; i++) {
            int num = nums[i];
            if (i == 2) {
                max[i] = num + max[i - 2];
            } else if (i < end) {
                max[i] = num + Math.max(max[i - 3], max[i - 2]);
            }
        }
        return Math.max(max[end - 2], max[end - 1]) + nums[0];
    }

    int dontRobFirstHouse(int[] nums) {
        int length = nums.length;
        int end = length - 1;
        int[] max = new int[length];
        max[0] = 0;
        max[1] = nums[1];
        for (int i = 2; i < length; i++) {
            int num = nums[i];
            if (i == 2) {
                max[i] = num + max[i - 2];
            } else {
                max[i] = num + Math.max(max[i - 3], max[i - 2]);
            }
        }
        return Math.max(max[end - 1], max[end]);
    }
}
