package fr.ninauve.renaud.leetcode.divide2integers;

// https://leetcode.com/problems/divide-two-integers/description/
public class Divide2IntegersBinary {

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

            int result = 0;
            boolean carry = false;
            int a = value();
            int b = other.value();
            for (int i = 0; i < 32; i++) {
                int mask = 1 << i;
                int digitA = (a & mask) >> i;
                int digitB = (b & mask) >> i;
                final int digitResult;
                if (digitA == 0) {
                    if (digitB == 0) {
                        digitResult = carry ? 1 : 0;
                        carry = false;
                    } else {
                        digitResult = carry ? 0 : 1;
                    }
                } else if (digitB == 0) {
                    digitResult = carry ? 0 : 1;
                } else {
                    digitResult = carry ? 1 : 0;
                    carry = true;
                }
                int digitResultMask = digitResult << i;
                result |= digitResultMask;
            }
            if (carry) {
                return MAX_UNSIGNED;
            }
            Unsigned32 result32 = new Unsigned32(result);
            if (result32.negativeBitIsSet()) {
                return MAX_UNSIGNED;
            }
            return result32;
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
