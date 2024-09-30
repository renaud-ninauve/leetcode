package fr.ninauve.renaud.leetcode.wildcardmatching;

public class WildCardMatching {

    private static final char ANY_CHAR = '?';
    private static final char ANY_SUBSEQUENCE = '*';

    public boolean isMatch(String string, String pattern) {
        if (!pattern.isEmpty() && pattern.charAt(0) == ANY_SUBSEQUENCE) {
            return true;
        }
        if (pattern.indexOf(ANY_SUBSEQUENCE) < 0 && string.length() != pattern.length()) {
            return false;
        }
        for (int i = 0; i < string.length(); i++) {
            char actualChar = string.charAt(i);
            char expectedChar = pattern.charAt(i);
            if (expectedChar == ANY_CHAR || expectedChar == ANY_SUBSEQUENCE) {
                continue;
            }
            if (actualChar != expectedChar) {
                return false;
            }
        }
        return true;
    }
}
