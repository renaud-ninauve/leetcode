package fr.ninauve.renaud.leetcode.mediansortedarrays;

// https://leetcode.com/problems/median-of-two-sorted-arrays
public class MedianOfSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return medianOf(nums2);
        }
        return 0;
    }

    private double medianOf(int[] nums) {
        if (nums.length %2 == 0) {
            int middleFloor = nums.length / 2 - 1;
            return (nums[middleFloor] + nums[middleFloor+1])/2.0;
        }
        return nums[nums.length/2];
    }
}