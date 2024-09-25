package fr.ninauve.renaud.leetcode.longestparentheses;

// https://leetcode.com/problems/longest-valid-parentheses/
public class LongestParentheses {
    private static final char OPEN = '(';
    private static final char CLOSE= ')';

    public int longestValidParentheses(String s) {
        boolean valid = true;
        int open = 0;
        int maxValid = 0;
        int currentValid = 0;
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == OPEN) {
                open++;
                valid = true;
            } else if (c == CLOSE) {
                if (open > 0) {
                    open--;
                } else {
                    valid = false;
                    currentValid = 0;
                    maxValid = Math.max(currentValid, maxValid);
                }
            }
            if (valid) {
                currentValid++;
            }
        }
        return valid ? Math.max(maxValid, currentValid) : maxValid;
    }
}
