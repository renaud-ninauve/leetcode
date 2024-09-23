package fr.ninauve.renaud.leetcode.mediansortedarrays;

public class MedianArray {
    private final int[] sortedNumbers;
    private int start;
    private int endExclusive;

    public static final MedianArray fromSortedNumbers(int[] numbers) {
        return new MedianArray(numbers);
    }

    public MedianArray(int[] sortedNumbers) {
        this.sortedNumbers = sortedNumbers;
        this.start = 0;
        this.endExclusive = sortedNumbers.length;
    }

    public double median() {
        int middle = start + length() / 2;
        if (length() % 2 == 0) {
            return (sortedNumbers[middle-1] + sortedNumbers[middle]) / 2.0;
        }
        return sortedNumbers[middle];
    }

    public int halfMedianInclusiveCount() {
        int middle = length() / 2;
        if (length() % 2 == 0) {
            return middle;
        }
        return middle + 1;
    }

    public int length() {
        return endExclusive - start;
    }

    public void removeBellowOrEqualToMedian() {
        this.start = this.start + halfMedianInclusiveCount();
    }

    public void removeOverOrEqualToMedian() {
        this.endExclusive = this.endExclusive - halfMedianInclusiveCount();
    }
}
