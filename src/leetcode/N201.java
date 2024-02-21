package leetcode;

// Bitwise AND of Numbers Range
public class N201 {

    public static void main(String[] args) {
        new N201().rangeBitwiseAnd(4, 7);
    }

    public int rangeBitwiseAnd(int left, int right) {
        int count = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            count += 1;
        }

        return (left << count);
    }
}
