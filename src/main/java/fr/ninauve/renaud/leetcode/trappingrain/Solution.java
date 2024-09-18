package fr.ninauve.renaud.leetcode.trappingrain;

import java.util.Arrays;

// https://leetcode.com/problems/trapping-rain-water/
public class Solution {

    public int trap(int[] height) {
        boolean left = false;
        int emptyAfterLeft = 0;
        int trapped = 0;
        for(int columnHeight: height) {
            if (!left) {
                if (columnHeight > 0) {
                    left = true;
                    emptyAfterLeft = 0;
                }
                continue;
            }
            if (columnHeight > 0) {
                trapped += emptyAfterLeft;
                emptyAfterLeft = 0;
            } else {
                emptyAfterLeft++;
            }
        }
        return trapped;
    }
}
