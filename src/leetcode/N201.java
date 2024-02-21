package leetcode;

// Bitwise AND of Numbers Range
public class N201 {

    public static void main(String[] args) {
        new N201().rangeBitwiseAnd(4, 7);
    }

    public int rangeBitwiseAnd(int left, int right) {
        int shift = 31 ^ Integer.numberOfLeadingZeros(left ^ right | 1);
        return left >>> shift << shift;
    }
}
