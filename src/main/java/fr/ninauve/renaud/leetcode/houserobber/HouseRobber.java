package fr.ninauve.renaud.leetcode.houserobber;

import java.util.LinkedList;
import java.util.Queue;

public class HouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int lastHouse = nums.length - 1;
        int max = 0;
        Queue<Rob> queue = new LinkedList<>();
        queue.offer(new Rob(0, nums[0]));
        queue.offer(new Rob(1, nums[1]));
        while(!queue.isEmpty()) {
            Rob current = queue.poll();
            if (current.house() >= lastHouse - 1) {
                max = Math.max(current.totalRobbed(), max);
                continue;
            }
            int next2 = current.house() + 2;
            if (next2 <= lastHouse) {
                queue.offer(new Rob(next2, current.totalRobbed() + nums[next2]));
            }
            int next3 = current.house() + 3;
            if (next3 <= lastHouse) {
                queue.offer(new Rob(next3, current.totalRobbed() + nums[next3]));
            }
        }
        return max;
    }

    record Rob(int house, int totalRobbed){}
}
