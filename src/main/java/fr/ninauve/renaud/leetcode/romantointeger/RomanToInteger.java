package fr.ninauve.renaud.leetcode.romantointeger;

public class RomanToInteger {
    public int romanToInt(String romanNumber) {
        int romanNumberLength = romanNumber.length();

        int sum = 0;
        for (int i = 0; i < romanNumberLength; i++) {
            int currentNumber = romanCharToInt(romanNumber.charAt(i));

            boolean isLastRomanChar = i == romanNumberLength - 1;
            if (isLastRomanChar) {
                sum += currentNumber;
                continue;
            }

            int nextNumber = romanCharToInt(romanNumber.charAt(i + 1));
            if (currentNumber < nextNumber) {
                sum -= currentNumber;
            } else {
                sum += currentNumber;
            }
        }
        return sum;
    }

    private int romanCharToInt(char romanChar) {
        return switch (romanChar) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> throw new IllegalArgumentException();
        };
    }
}
