package fr.ninauve.renaud.leetcode.trappingrain;

import java.util.Arrays;
import java.util.stream.IntStream;

// https://leetcode.com/problems/trapping-rain-water/
public class Solution {

    public int trap(int[] heights) {
        boolean first = true;
        int previous = 0;
        int retainedWater = 0;
        for(int height: heights) {
            if (first) {
                first = false;
                previous = height;
                continue;
            }
            if (height < previous) {
                retainedWater += previous - height;
            }
        }
        return retainedWater;
    }
}
