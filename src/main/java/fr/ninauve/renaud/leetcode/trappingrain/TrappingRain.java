package fr.ninauve.renaud.leetcode.trappingrain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// https://leetcode.com/problems/trapping-rain-water/
public class TrappingRain {

    public int trap(int[] height) {
        List<Integer> maxLeft = maxLeft(height);
        List<Integer> maxRight = maxRight(height);
        int water = 0;
        for(int i=0; i<height.length; i++) {
            Integer left = maxLeft.get(i);
            Integer right = maxRight.get(i);
            int maxWater = Math.min(left, right);
            if (maxWater > height[i]) {
                water += maxWater - height[i];
            }
        }
        return water;
    }

    private List<Integer> maxLeft(int[] height) {
        List<Integer> left = new ArrayList<>(height.length);
        int previous = 0;
        for(int i=0; i<height.length; i++) {
            int currentHeight = height[i];
            int max = Math.max(previous, currentHeight);
            previous = max;
            left.add(max);
        }
        return left;
    }

    private List<Integer> maxRight(int[] height) {
        List<Integer> right = IntStream.rangeClosed(1, height.length)
                .map(i -> 0)
                .boxed()
                .collect(Collectors.toList());
        int previous = 0;
        for(int i=height.length-1; i>=0; i--) {
            int currentHeight = height[i];
            int max = Math.max(previous, currentHeight);
            previous = max;
            right.set(i, max);
        }
        return right;
    }
}
