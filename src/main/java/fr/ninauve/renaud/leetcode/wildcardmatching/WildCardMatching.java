package fr.ninauve.renaud.leetcode.wildcardmatching;

public class WildCardMatching {
    public boolean isMatch(String string, String pattern) {
        if (string.length() != pattern.length()) {
            return false;
        }
        for(int i=0; i<string.length(); i++) {
            char actualChar = string.charAt(i);
            char expectedChar = pattern.charAt(i);
            if (expectedChar == '?') {
                continue;
            }
            if (actualChar != expectedChar) {
                return false;
            }
        }
        return true;
    }
}
