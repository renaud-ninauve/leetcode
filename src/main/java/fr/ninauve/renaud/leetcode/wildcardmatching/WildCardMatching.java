package fr.ninauve.renaud.leetcode.wildcardmatching;

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
        String stringToProcess = string;
        boolean wild = false;
        int matchedPattern = 0;
        while (matchedPattern < splittedPattern.length && !stringToProcess.isEmpty()) {
            String subPattern = splittedPattern[matchedPattern];
            wild = subPattern.isEmpty() || matchedPattern > 0;
            if (subPattern.isEmpty()) {
                matchedPattern++;
                continue;
            }
            MatchResult matchResult = matchSubPattern(stringToProcess, subPattern);
            if (!matchResult.matches) {
                return false;
            }
            if (!wild && matchResult.start != 0) {
                return false;
            }
            matchedPattern++;
            stringToProcess = stringToProcess.substring(Math.min(matchResult.end + 1, stringToProcess.length()));
        }
        return (matchedPattern == splittedPattern.length && stringToProcess.isEmpty()) ||
                (matchedPattern >= splittedPattern.length - 1 &&
                        (!stringToProcess.isEmpty() && matchLastPattern(stringToProcess, splittedPattern) || pattern.charAt(pattern.length() - 1) == ANY_SUBSEQUENCE));
    }

    boolean matchLastPattern(String string, String[] splittedPattern) {
        String lastPattern = splittedPattern[splittedPattern.length - 1];
        String endOfString = string.substring(string.length() - lastPattern.length());
        MatchResult matchResult = matchSubPattern(endOfString, lastPattern);
        return matchResult.matches;
    }

    MatchResult matchSubPattern(String string, String pattern) {
        for (int start = 0; start < string.length(); start++) {
            int i = start;
            int matchLength = 0;
            boolean matches = true;
            char actual;
            char expected;
            while (matches && i < string.length()) {
                actual = string.charAt(i);
                expected = pattern.charAt(matchLength);
                if (actual == expected || expected == ANY_CHAR) {
                    matchLength++;
                    i++;
                    if (matchLength == pattern.length()) {
                        return MatchResult.found(start, start + pattern.length() - 1);
                    }
                } else {
                    matches = false;
                }
            }
        }
        return MatchResult.notFound();
    }

    static class MatchResult {
        final int start;
        final int end;
        final boolean matches;

        static MatchResult found(int start, int end) {
            return new MatchResult(start, end, true);
        }

        static MatchResult notFound() {
            return new MatchResult(-1, -1, false);
        }

        MatchResult(int start, int end, boolean matches) {
            this.start = start;
            this.end = end;
            this.matches = matches;
        }
    }
}
