package fr.ninauve.renaud.leetcode.wildcardmatching;

public class WildCardMatching {
    public boolean isMatch(String string, String pattern) {
        return pattern.isEmpty() && string.isEmpty();
    }
}
