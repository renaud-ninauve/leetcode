package fr.ninauve.renaud.leetcode.problems.divide2integers;

// https://leetcode.com/problems/divide-two-integers/description/
public class Divide2Integers {

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        Sign dividendSign = Sign.of(dividend);
        Sign divisorSign = Sign.of(divisor);
        Sign resultSign = dividendSign.multiply(divisorSign);

        Unsigned32 result32 = dividePositives(new Unsigned32(Math.abs(dividend)), new Unsigned32(Math.abs(divisor)));
        return resultSign == Sign.POSITIVE ?
                result32.toPositive() : result32.toNegative();
    }

    private Unsigned32 dividePositives(Unsigned32 dividend, Unsigned32 divisor) {
        if (dividend == divisor) {
            return new Unsigned32(1);
        }
        if (divisor.equals(new Unsigned32(1))) {
            return dividend;
        }
        if (divisor.equals(new Unsigned32(2))) {
            return new Unsigned32(dividend.value() >> 1);
        }
        if (dividend.lessThan(divisor)) {
            return new Unsigned32(0);
        }
        Unsigned32 result = new Unsigned32(0);
        Unsigned32 current = new Unsigned32(0);
        while (current.lessThan(dividend) && !current.isMax()) {
            current = current.add(divisor);
            if (current.lessThanOrEqualTo(dividend)) {
                result = result.add(new Unsigned32(1));
            }
        }
        return result;
    }

    static final Unsigned32 MAX_UNSIGNED = new Unsigned32(0x80000000);
    record Unsigned32(int value) {
        boolean isMax() {
            return this.equals(MAX_UNSIGNED);
        }
        boolean negativeBitIsSet() {
            return value < 0;
        }
        boolean lessThan(Unsigned32 other) {
            if (this.value() == other.value()) {
                return false;
            }
            if (this.isMax()) {
                return false;
            }
            if (other.isMax()) {
                return true;
            }
            return value() < other.value();
        }
        boolean lessThanOrEqualTo(Unsigned32 other) {
            if (this.value() == other.value()) {
                return true;
            }
            if (this.isMax()) {
                return false;
            }
            if (other.isMax()) {
                return true;
            }
            return value() < other.value();
        }
        boolean greaterThan(Unsigned32 other) {
            if (this.value() == other.value()) {
                return false;
            }
            if (this.isMax()) {
                return true;
            }
            if (other.isMax()) {
                return false;
            }
            return value() > other.value();
        }
        Unsigned32 add(Unsigned32 other) {
            if (isMax() || other.isMax()) {
                return MAX_UNSIGNED;
            }
            int result = this.value() + other.value();
            return result < 0 ? MAX_UNSIGNED : new Unsigned32(result);
        }
        int toPositive() {
            if (negativeBitIsSet()) {
                return 0x7fffffff;
            }
            return value;
        }
        int toNegative() {
            if (negativeBitIsSet()) {
                return value;
            }
            return -value;
        }
    }

    enum Sign {
        POSITIVE, NEGATIVE;

        static Sign of(int number) {
            return number < 0 ? NEGATIVE : POSITIVE;
        }

        Sign multiply(Sign other) {
            if (this == POSITIVE) {
                return other == POSITIVE ? POSITIVE : NEGATIVE;
            }
            return other == POSITIVE ? NEGATIVE : POSITIVE;
        }
    }
}
