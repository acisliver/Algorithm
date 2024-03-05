package leetcode;

// Minimum Length of String After Deleting Similar Ends
public class N1750 {
    public int minimumLength(String s) {
        int l = 0;
        int r = s.length() - 1;
        char[] chars = s.toCharArray();

        while (l < r) {
            char left = chars[l];
            char right = chars[r];
            if (left != right) {
                return r - l + 1;
            }

            while (l <= r) {
                if (left == chars[l + 1]) {
                    l += 1;
                } else {
                    l += 1;
                    break;
                }
            }
            while (l <= r) {
                if (right == chars[r - 1]) {
                    r -= 1;
                } else {
                    r -= 1;
                    break;
                }
            }
        }

        return r - l + 1;
    }
}
