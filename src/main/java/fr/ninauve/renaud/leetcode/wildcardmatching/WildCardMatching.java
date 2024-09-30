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
        SubsequenceMatcher matcher = new SubsequenceMatcher(pattern);
        for (int i = 0; i < string.length(); i++) {
            char actualChar = string.charAt(i);
            matcher.actualChar(actualChar);
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    private static class SubsequenceMatcher {
        private final String pattern;
        private int matchLength = 0;

        private SubsequenceMatcher(String pattern) {
            this.pattern = pattern;
        }

        private void actualChar(char actual) {
            if (matchLength < 0 || matchLength >= pattern.length()) {
                return;
            }
            char expected = pattern.charAt(matchLength);
            boolean matches = expected == ANY_CHAR || actual == expected;
            if (matches) {
                matchLength++;
            } else {
                matchLength = -1;
            }
        }

        private boolean matches() {
            return matchLength >= 0;
        }

        private int getMatchLength() {
            return matchLength;
        }
    }
}
