package fr.ninauve.renaud.leetcode.wildcardmatching;

import java.util.Arrays;
import java.util.List;

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
        String[] subPatterns = pattern.split("\\*");
        List<SubsequenceMatcher> matchers = Arrays.stream(subPatterns)
                .map(SubsequenceMatcher::new)
                .toList();
        int matcherIndex = 0;
        SubsequenceMatcher matcher = matchers.get(matcherIndex);
        for (int i = 0; i < string.length(); i++) {
            if (matcher.matchesFully()) {
                if (matcherIndex >= matchers.size() - 1) {
                    return false;
                }
                matcherIndex++;
                matcher = matchers.get(matcherIndex);
            }
            char actualChar = string.charAt(i);
            matcher.actualChar(actualChar);
            if (!matcher.matchesPartially()) {
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

        private boolean matchesPartially() {
            return matchLength >= 0;
        }

        private boolean matchesFully() {
            return matchLength == pattern.length();
        }

        private int getMatchLength() {
            return matchLength;
        }
    }
}
