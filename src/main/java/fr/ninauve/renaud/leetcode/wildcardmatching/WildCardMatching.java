package fr.ninauve.renaud.leetcode.wildcardmatching;

import java.util.ArrayList;
import java.util.List;

public class WildCardMatching {

    private static final char ANY_CHAR = '?';
    private static final char ANY_SUBSEQUENCE = '*';

    public boolean isMatch(String string, String pattern) {
        final String patternWithoutDuplicates = pattern.replaceAll("\\" + ANY_SUBSEQUENCE + "+", "" + ANY_SUBSEQUENCE);
        return isMatchPattern(string, patternWithoutDuplicates);
    }

    private boolean isMatchPattern(String string, String pattern) {
        if (pattern.isEmpty()) {
            return string.isEmpty();
        }
        if (pattern.equals("" + ANY_SUBSEQUENCE)) {
            return true;
        }

        String[] splittedPattern = pattern.split("\\" + ANY_SUBSEQUENCE, -1);
        Matcher matcher = null;
        int subPatternIndex = -1;
        for (int i = 0; i < string.length(); i++) {
            char actualChar = string.charAt(i);
            if (matcher == null) {
                if (splittedPattern[0].isEmpty()) {
                    matcher = new AfterWildMatcher(splittedPattern[1]);
                    subPatternIndex = 1;
                } else {
                    matcher = new SimpleMatcher(splittedPattern[0]);
                    subPatternIndex = 0;
                }
            }
            if (matcher.matchedFully()) {
                if (subPatternIndex < splittedPattern.length - 1) {
                    subPatternIndex++;
                    if (splittedPattern[subPatternIndex].isEmpty()) {
                        return true;
                    }
                    matcher = new AfterWildMatcher(splittedPattern[subPatternIndex]);
                } else {
                    return false;
                }
            }
            if (!matcher.mayMatch(actualChar)) {
                return false;
            }
        }
        return matcher.matchedFully()
                && (subPatternIndex == splittedPattern.length - 1 || subPatternIndex == splittedPattern.length - 2 && splittedPattern[splittedPattern.length - 1].isEmpty());
    }

    private interface Matcher {
        boolean mayMatch(char actualChar);

        boolean matchesEndOfString();

        boolean matchedFully();
    }

    private static class AfterWildMatcher implements Matcher {
        private final String pattern;
        private List<SimpleMatcher> matchers = new ArrayList<>();

        private AfterWildMatcher(String pattern) {
            this.pattern = pattern;
        }

        @Override
        public boolean mayMatch(char actualChar) {
            final List<SimpleMatcher> allMatchers = new ArrayList<>(matchers);
            SimpleMatcher newMatcher = new SimpleMatcher(pattern);
            allMatchers.add(newMatcher);
            matchers = allMatchers.stream().filter(m -> m.mayMatch(actualChar))
                    .toList();
            return true;
        }

        @Override
        public boolean matchesEndOfString() {
            final List<SimpleMatcher> allMatchers = new ArrayList<>(matchers);
            SimpleMatcher newMatcher = new SimpleMatcher(pattern);
            allMatchers.add(newMatcher);
            matchers = allMatchers.stream().filter(SimpleMatcher::matchesEndOfString)
                    .toList();
            return !matchers.isEmpty();
        }

        @Override
        public boolean matchedFully() {
            return matchers.stream().anyMatch(SimpleMatcher::matchedFully);
        }
    }

    private static class SimpleMatcher implements Matcher {
        private final String pattern;
        private int index = -1;

        private SimpleMatcher(String pattern) {
            this.pattern = pattern;
        }

        @Override
        public boolean mayMatch(char actualChar) {
            index++;
            if (index >= pattern.length()) {
                return false;
            }
            char expected = pattern.charAt(index);
            return actualChar == expected
                    || expected == ANY_CHAR;
        }

        @Override
        public boolean matchesEndOfString() {
            return matchedFully();
        }

        @Override
        public boolean matchedFully() {
            return index == pattern.length() - 1;
        }
    }
}
