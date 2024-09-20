package fr.ninauve.renaud.leetcode.multiplystrings;

// https://leetcode.com/problems/multiply-strings
public class MultiplyStrings {

    public String multiply(String a, String b) {
        return "" + Integer.parseInt(a.substring(0, 1)) * Integer.parseInt(b.substring(0, 1));
    }
}
