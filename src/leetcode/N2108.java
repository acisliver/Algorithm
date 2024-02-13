package leetcode;

// Find First Palindromic String in the Array
public class N2108 {
    public String firstPalindrome(String[] words) {
        for (String word : words) {
            if (isPalindrome(word)) {
                return word;
            }
        }

        return "";
    }

    private boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;

        char[] chars = s.toCharArray();
        while (l < r) {
            if (chars[l] != chars[r]) {
                return false;
            }
            l += 1;
            r -= 1;
        }

        return true;
    }
}
