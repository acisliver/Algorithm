package leetcode;

// https://leetcode.com/problems/backspace-string-compare/submissions/
// Two Pointers
public class N844 {
    public boolean backspaceCompare(String s, String t) {
        return getString(s).equals(getString(t));
    }

    public String getString(String str) {
        int p = str.length() - 1;
        int deleteCount = 0;
        StringBuilder result = new StringBuilder();

        while(p >= 0) {
            if (str.charAt(p) == '#') {
                deleteCount++;
            } else {
                if (deleteCount > 0) {
                    deleteCount--;
                } else {
                    result.append(str.charAt(p));
                }
            }
            p--;
        }

        return result.toString();
    }
}
