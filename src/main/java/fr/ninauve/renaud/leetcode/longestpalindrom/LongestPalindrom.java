package fr.ninauve.renaud.leetcode.longestpalindrom;

import java.util.Collection;
import java.util.HashSet;

// https://leetcode.com/problems/longest-palindromic-substring/
public class LongestPalindrom {
    String str;
    Collection<Integer> palindromStarts = new HashSet<>();
    String maxPalindrom = "";

    public String longestPalindrome(String s) {
        this.str = s;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            final Collection<Integer> newStarts = new HashSet<>();

            if (i > 0 && c == s.charAt(i - 1)) {
                startPalindrom(newStarts, i - 1, i);
            }
            if (i >= 2 && c == s.charAt(i - 2)) {
                startPalindrom(newStarts, i - 2, i);
            }
            for (int start : palindromStarts) {
                char startChar = s.charAt(start - 1);
                if (c == startChar) {
                    startPalindrom(newStarts, start - 1, i);
                } else {
                    endPalindrom(start, i - 1);
                }
            }
            palindromStarts = newStarts;
        }
        for (int start : palindromStarts) {
            endPalindrom(start, str.length() - 1);
        }
        if (maxPalindrom.isEmpty()) {
            return s.isEmpty() ? "" : s.substring(0, 1);
        }
        return maxPalindrom;
    }

    private void startPalindrom(Collection<Integer> newStarts, int start, int end) {
        if (start == 0) {
            endPalindrom(start, end);
        } else {
            newStarts.add(start);
        }
    }

    private void endPalindrom(int start, int end) {
        String palindrom = str.substring(start, end + 1);
        maxPalindrom = maxPalindrom.length() >= palindrom.length() ? maxPalindrom : palindrom;
    }
}
