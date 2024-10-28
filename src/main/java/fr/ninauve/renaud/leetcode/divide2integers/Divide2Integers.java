package fr.ninauve.renaud.leetcode.divide2integers;

import java.util.Objects;
import java.util.function.Function;

// https://leetcode.com/problems/divide-two-integers/description/
public class Divide2Integers {
    private static final int SHIFT_MAX = 4;

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        int positiveDividend = Math.abs(dividend);
        int positiveDivisor = Math.abs(divisor);
        boolean resultIsNegative = dividend > 0 && divisor < 0
                || dividend < 0 && divisor > 0;

        if (positiveDividend == positiveDivisor) {
            return resultIsNegative ? -1 : 1;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (positiveDivisor == 1) {
            return resultIsNegative ? -positiveDividend : positiveDividend;
        }
        if (positiveDivisor > positiveDividend) {
            return 0;
        }
        Approximation approximation = approximate(positiveDividend, positiveDivisor);
        int positiveResult = exactResult(positiveDividend, positiveDivisor, approximation);
        return resultIsNegative ? -positiveResult : positiveResult;
    }

    BinarySlice nextDividend(int dividend, int divisor, int highestPow) {
        int nextDividend = 0;
        int smallestPow = highestPow;
        while (nextDividend < divisor && smallestPow >= 0) {
            nextDividend = binaryAt(dividend, smallestPow, highestPow);
            smallestPow--;
        }
        if (nextDividend >= divisor) {
            return new BinarySlice(nextDividend, smallestPow + 1, highestPow);
        }
        return new BinarySlice(0, 0, highestPow);
    }

    static class BinarySlice {
        private final int value;
        private final int smallestPow;
        private final int highestPow;

        BinarySlice(int value, int smallestPow, int highestPow) {
            this.value = value;
            this.smallestPow = smallestPow;
            this.highestPow = highestPow;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BinarySlice that)) return false;
            return value == that.value && smallestPow == that.smallestPow && highestPow == that.highestPow;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, smallestPow, highestPow);
        }

        @Override
        public String toString() {
            return "BinarySlice{" +
                    "value=" + value +
                    ", startPow=" + smallestPow +
                    ", endPow=" + highestPow +
                    '}';
        }
    }

    int binaryAt(int value, int pow) {
        int mask = 1 << pow;
        return (value & mask) >> pow;
    }

    int binaryAt(int value, int startPow, int endPow) {
        int result = 0;
        int pow = 0;
        for (int i = startPow; i <= endPow; i++) {
            int binValue = binaryAt(value, i);
            result += (binValue << pow);
            pow++;
        }
        return result;
    }

    private Approximation approximate(int positiveDividend, int positiveDivisor) {
        Approximation approximation = new Approximation(0, 0);
        for (int shift = SHIFT_MAX; shift > 0; shift--) {
            approximation = approximate(positiveDividend, positiveDivisor, approximation,
                    rotateLeft(shift), rotateRight(shift), rotateLeft(shift), rotateRight(shift));
        }
        for (int shift = 32; shift > 0; shift--) {
            approximation = approximate(positiveDividend, positiveDivisor, approximation,
                    incrementPow2(positiveDivisor, shift), decrementPow2(positiveDivisor, shift),
                    incrementPow2(1, shift), decrementPow2(1, shift));
        }
        return approximation;
    }

    private Function<Integer, Integer> incrementPow2(int increment, int shift) {
        return i -> i + Integer.rotateLeft(increment, shift);
    }

    private Function<Integer, Integer> decrementPow2(int decrement, int shift) {
        return i -> i - Integer.rotateLeft(decrement, shift);
    }

    private Function<Integer, Integer> rotateRight(int shift) {
        return i -> Integer.rotateRight(i, shift);
    }

    private Function<Integer, Integer> rotateLeft(int shift) {
        return i -> Integer.rotateLeft(i, shift);
    }

    private Approximation approximate(int positiveDividend, int positiveDivisor, Approximation approximation,
                                      Function<Integer, Integer> totalIncrement, Function<Integer, Integer> totalDecrement,
                                      Function<Integer, Integer> resultIncrement, Function<Integer, Integer> rseultDecrement) {
        int total = approximation.total;
        int result = approximation.result;
        while (total < positiveDividend) {
            if (total == 0) {
                total = positiveDivisor;
                result = 1;
            } else {
                int oldTotal = total;
                int oldResult = result;
                total = totalIncrement.apply(total);
                result = resultIncrement.apply(result);
                if (total < oldTotal || result < oldResult) {
                    break;
                }
            }
        }
        if (total == positiveDividend) {
            return new Approximation(result, total);
        }
        total = totalDecrement.apply(total);
        result = rseultDecrement.apply(result);
        return new Approximation(result, total);
    }

    private int exactResult(int positiveDividend, int positiveDivisor, Approximation approximation) {
        int total = approximation.total;
        int result = approximation.result;
        while (total < positiveDividend) {
            total += positiveDivisor;
            result++;
        }
        return total == positiveDividend ? result : result - 1;
    }

    private static class Approximation {
        private final int result;
        private final int total;

        public Approximation(int result, int total) {
            this.result = result;
            this.total = total;
        }
    }
}
