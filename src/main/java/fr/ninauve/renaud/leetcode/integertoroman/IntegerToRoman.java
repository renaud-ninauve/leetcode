package fr.ninauve.renaud.leetcode.integertoroman;

public class IntegerToRoman {
    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        int remaining = num;
        for(int i = 1000; i> 0; i /= 10){
            int unitNumber = remaining / i;
            remaining = remaining - (unitNumber * i);
            if(unitNumber == 0) continue;
            result.append(toRomanNumberBelowTen(unitNumber,i));
        }
        return result.toString();
    }

    private static String toRomanNumberBelowTen(int arabicNumber, int powTen) {
        if (arabicNumber <= 3) {
            return toRomanSymbol(powTen).repeat(arabicNumber);
        }
        if (arabicNumber == 4) {
            return toRomanSymbol(powTen) + toRomanSymbol(5*powTen);
        }
        if (5 < arabicNumber && arabicNumber < 9) {
            return toRomanSymbol(5*powTen) + toRomanSymbol(powTen).repeat(arabicNumber - 5);
        }
        if (arabicNumber == 9) {
            return toRomanSymbol(powTen) + toRomanSymbol(10*powTen);
        }
        if (arabicNumber == 5) {
            return toRomanSymbol(5*powTen);
        }
        throw new IllegalArgumentException();
    }

    private static String toRomanSymbol(int arabicNumber) {
        return switch (arabicNumber) {
            case 1 -> "I";
            case 5 -> "V";
            case 10 -> "X";
            case 50 -> "L";
            case 100 -> "C";
            case 500 -> "D";
            case 1000 -> "M";
            default -> throw new IllegalArgumentException();
        };
    }
}
