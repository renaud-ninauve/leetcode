package fr.ninauve.renaud.leetcode.sortcolors;

// https://leetcode.com/problems/sort-colors/
public class SortColors {
    public void sortColors(int[] nums) {
        int zeros = 0;
        int ones = 0;
        int twos = 0;
        for (int num : nums) {
            if (num == 0) {
                zeros++;
            } else if (num == 1) {
                ones++;
            } else {
                twos++;
            }
        }

        for (int i = 0; i < zeros; i++) {
            nums[i] = 0;
        }
        for (int i = 0; i < ones; i++) {
            nums[zeros + i] = 1;
        }
        for (int i = 0; i < twos; i++) {
            nums[zeros + ones + i] = 2;
        }
    }
}
