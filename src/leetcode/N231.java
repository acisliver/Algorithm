package leetcode;

// Power of Two
public class N231 {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        // return (n & (n - 1)) == 0;
        return Integer.bitCount(n) == 1;
    }
}
