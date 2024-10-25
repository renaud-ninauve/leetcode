package fr.ninauve.renaud.leetcode.stringtointeger;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// https://leetcode.com/problems/string-to-integer-atoi/
public class StringToInteger {
    private static final Pattern NUMBER = Pattern.compile("^\\s*([+-])?0*(\\d+)");
    private static final String MINUS = "-";
    private static final String UNSIGNED_MIN_NEGATIVE = "2147483648";
    private static final String MAX_POSITIVE = "2147483647";
    private static final int MIN_VALUE = -2147483648;
    private static final int MAX_VALUE = 2147483647;

    public int myAtoi(String s) {
        Matcher matcher = NUMBER.matcher(s);
        if (!matcher.find()) {
            return 0;
        }
        int sign = MINUS.equals(matcher.group(1)) ? -1 : 1;
        String digits = matcher.group(2);
        if (sign == -1 && isGreaterThan(digits, UNSIGNED_MIN_NEGATIVE)) {
            return MIN_VALUE;
        }
        if (sign == 1 && isGreaterThan(digits, MAX_POSITIVE)) {
            return MAX_VALUE;
        }
        int result = toUnsignedNumber(digits);
        return result * sign;
    }

    private int toUnsignedNumber(String digits) {
        int result = 0;
        for (int i = 0; i < digits.length(); i++) {
            char digitChar = digits.charAt(i);
            int digit = digitChar - '0';
            int pow = (int) Math.pow(10, digits.length() - i - 1);
            result += (digit * pow);
        }
        return result;
    }

    private boolean isGreaterThan(String digits, String other) {
        Comparator<String> comparator = Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder());
        return comparator.compare(digits, other) >= 0;
    }
}
