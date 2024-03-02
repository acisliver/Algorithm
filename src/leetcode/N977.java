package leetcode;

import java.util.Arrays;

// Squares of a Sorted Array
public class N977 {
    public int[] sortedSquares(int[] nums) {
        return Arrays.stream(nums)
                .map(n -> n * n)
                .sorted()
                .toArray();
    }
}
