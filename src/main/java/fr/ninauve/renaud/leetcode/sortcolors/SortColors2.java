package fr.ninauve.renaud.leetcode.sortcolors;

// https://leetcode.com/problems/sort-colors/
public class SortColors2 {
    int zeros = 0;
    int twos = 0;

    public void sortColors(int[] nums) {
        zeros = 0;
        twos = 0;

        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (value == 0) {
                sortZero(nums, i);
            }
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            int value = nums[i];
            if (value == 2) {
                sortTwo(nums, i);
            }
        }
    }

    private void sortZero(int[] nums, int index) {
        swap(nums, index, zeros);
        zeros++;
    }

    private void sortTwo(int[] nums, int index) {
        swap(nums, index, nums.length - 1 - twos);
        twos++;
    }

    private void swap(int[] nums, int index1, int index2) {
        int value1 = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = value1;
    }
}
